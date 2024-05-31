package kuit.server.service;

import kuit.server.common.exception.BadRequestException;
import kuit.server.common.exception.UserException;
import kuit.server.domain.User;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.dto.user.PostUserResponse;
import kuit.server.repository.UserRepository;
import kuit.server.type.Status;
import kuit.server.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  private final UserRepository userRepository;

  public PostUserResponse signUp(PostUserRequest postUserRequest) {
    log.info("[UserService.createUser]");

    // TODO: 1. validation (중복 검사)
    validateEmail(postUserRequest.getEmail());
    String nickname = postUserRequest.getNickname();
    if (nickname != null) {
      validateNickname(postUserRequest.getNickname());
    }

    // TODO: 2. password 암호화
    String encodedPassword = passwordEncoder.encode(postUserRequest.getPassword());
    postUserRequest.resetPassword(encodedPassword);

    // TODO: 3. DB insert & userId 반환
    User savedUser = userRepository.save(User.from(postUserRequest));

    // TODO: 4. JWT 토큰 생성
    String jwt = jwtTokenProvider.createToken(postUserRequest.getEmail(), savedUser.getUserId());

    return new PostUserResponse(savedUser.getUserId(), jwt);
  }

  public void modifyUserStatus(long userId, String status) {
    log.info("[UserService.modifyUserStatus]");

    User user = validateUserId(userId);
    Status selectedStatus = Status.get(status)
      .orElseThrow(() -> new BadRequestException(BAD_REQUEST));
    user.setStatus(selectedStatus.toString().toLowerCase());
    userRepository.save(user);
  }

  public GetUserResponse getUserInfo(long userId) {
    log.info("[UserService.getUserInfo]");
    User user = validateUserId(userId);
    return GetUserResponse.from(user);
  }

  public void modifyNickname(long userId, String nickname) {
    log.info("[UserService.modifyNickname]");

    validateNickname(nickname);
    User user = validateUserId(userId);

    user.setNickname(nickname);
    userRepository.save(user);
  }

  public List<GetUserResponse> getUserList(Pageable pageable) {
    log.info("[UserService.getUsers]");
    List<GetUserResponse> userList = new ArrayList<>();
    Page<User> users = userRepository.findAll(pageable);
    users.forEach(x -> userList.add(GetUserResponse.from(x)));
    return userList;
  }

  public User validateUserId(long userId) {
    return userRepository.findByUserId(userId)
      .orElseThrow(() -> new UserException(USER_NOT_FOUND));
  }

  public long getUserIdByEmail(String email) {
    User user = userRepository.findByEmail(email)
      .orElseThrow(() -> new UserException(USER_NOT_FOUND));
    return user.getUserId();
  }

  private void validateEmail(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new UserException(DUPLICATE_EMAIL);
    }
  }

  private void validateNickname(String nickname) {
    if (userRepository.existsByNickname(nickname)) {
      throw new UserException(DUPLICATE_NICKNAME);
    }
  }
}