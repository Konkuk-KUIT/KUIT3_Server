package kuit.server.service;

import kuit.server.common.exception.DatabaseException;
import kuit.server.common.exception.UserException;
import kuit.server.dao.UserDao;
import kuit.server.dto.user.*;
import kuit.server.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public PostUserResponse signUp(PostUserRequest postUserRequest) {
        log.info("[UserService.createUser]");

        // TODO: 1. validation (중복 검사)
        validateEmail(postUserRequest.getEmail());
        String name = postUserRequest.getName();
        if (name != null) {
            validateNickname(postUserRequest.getName());
        }

        // TODO: 2. password 암호화
        String encodedPassword = passwordEncoder.encode(postUserRequest.getPassword());
        postUserRequest.resetPassword(encodedPassword);

        // TODO: 3. DB insert & userId 반환
        long userid = userDao.createUser(postUserRequest);

        // TODO: 4. JWT 토큰 생성
        String jwt = jwtTokenProvider.createToken(postUserRequest.getEmail(), userid);

        return new PostUserResponse(userid, jwt);
    }

    public PostLoginResponse login(PostLoginRequest postLoginRequest) {
        log.info("[UserService.loginUser]");
        long userid=1;
        String jwt = jwtTokenProvider.createToken(postLoginRequest.getEmail(), userid);
        return new PostLoginResponse(userid,jwt);
    }

    public void modifyUserStatus_Inactive(long userid) {
        log.info("[UserService.modifyUserStatus_Inactive]");

        int affectedRows = userDao.modifyUserStatus_Inactive(userid);
        if (affectedRows != 1) {
            throw new UserException(USER_NOT_FOUND);
        }
    }

    public void modifyUserStatus_deleted(long userid) {
        log.info("[UserService.modifyUserStatus_deleted]");

        int affectedRows = userDao.modifyUserStatus_deleted(userid);
        if (affectedRows != 1) {
            throw new UserException(USER_NOT_FOUND);
        }
    }

    public void modifyNickname(long userid, String name) {
        log.info("[UserService.modifyNickname]");

        validateNickname(name);
        int affectedRows = userDao.modifyNickname(userid, name);
        if (affectedRows != 1) {
            throw new UserException(USER_NOT_FOUND);
        }
    }

    public List<GetUserResponse> getUsers(String name, String email, String status) {
        log.info("[UserService.getUsers]");
        return userDao.getUsers(name, email, status);
    }

    private void validateEmail(String email) {
        if (userDao.hasDuplicateEmail(email)) {
            throw new UserException(DUPLICATE_EMAIL);
        }
    }

    private void validateNickname(String name) {
        if (userDao.hasDuplicateNickName(name)) {
            throw new UserException(DUPLICATE_NICKNAME);
        }
    }


}