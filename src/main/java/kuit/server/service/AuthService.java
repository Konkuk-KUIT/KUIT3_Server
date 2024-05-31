package kuit.server.service;

import kuit.server.common.exception.UserException;
import kuit.server.domain.User;
import kuit.server.dto.auth.LoginRequest;
import kuit.server.dto.auth.LoginResponse;
import kuit.server.repository.UserRepository;
import kuit.server.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.EMAIL_NOT_FOUND;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.PASSWORD_NO_MATCH;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  public LoginResponse login(LoginRequest authRequest) {
    log.info("[AuthService.login]");

    String email = authRequest.getEmail();

    // TODO: 1. 이메일 유효성 확인
    User user = userRepository.findByEmail(email)
      .orElseThrow(() -> new UserException(EMAIL_NOT_FOUND));

    // TODO: 2. 비밀번호 일치 확인
    validatePassword(authRequest.getPassword(), user.getUserId());

    // TODO: 3. JWT 갱신
    String updatedJwt = jwtTokenProvider.createToken(email, user.getUserId());

    return new LoginResponse(user.getUserId(), updatedJwt);
  }

  private void validatePassword(String password, long userId) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new UserException(EMAIL_NOT_FOUND));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new UserException(PASSWORD_NO_MATCH);
    }
  }
}