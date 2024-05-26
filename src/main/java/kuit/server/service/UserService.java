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

    public List<GetUserResponse> getUsers(String name, String email, String status) {
        log.info("[UserService.getUsers]");
        return userDao.getUsers(name, email, status);
    }

    public PostUserResponse signUp(PostUserRequest postUserRequest) {
        log.info("[UserService.createUser]");

        // TODO: 1. validation (중복 검사)
        validateEmail(postUserRequest.getEmail());
        String name = postUserRequest.getName();
        if (name != null) {
            validateName(postUserRequest.getName());
        }

        // TODO: 2. password 암호화
        String encodedPassword = passwordEncoder.encode(postUserRequest.getPassword());
        postUserRequest.resetPassword(encodedPassword);

        // TODO: 3. DB insert & userId 반환
        long userId = userDao.createUser(postUserRequest);

        // TODO: 4. JWT 토큰 생성
        String jwt = jwtTokenProvider.createToken(postUserRequest.getEmail(), userId);
        return new PostUserResponse(userId, jwt);
    }

    public void updateUserByEmail(String email, PostUserRequest postUserRequest) {
        log.info("[UserService.updateUserByEmail");

        String encodedPassword = passwordEncoder.encode(postUserRequest.getPassword());
        postUserRequest.resetPassword(encodedPassword);

        int affectedRows = userDao.modifyUserByEmail(email, postUserRequest);
        if (affectedRows != 1) {
            throw new DatabaseException(DATABASE_ERROR);
        }
    }

    public void modifyUserStatus_inactive(long userId) {
        log.info("[UserService.modifyUserStatus_inactive]");

        int affectedRows = userDao.modifyUserStatus_inactive(userId);
        if (affectedRows != 1) {
            throw new DatabaseException(DATABASE_ERROR);
        }
    }

    public void modifyName(long userId, String name) {
        log.info("[UserService.modifyName]");

        validateName(name);
        int affectedRows = userDao.modifyName(userId, name);
        if (affectedRows != 1) {
            throw new DatabaseException(DATABASE_ERROR);
        }
    }

    private void validateEmail(String email) {
        if (userDao.hasDuplicateEmail(email)) {
            throw new UserException(DUPLICATE_EMAIL);
        }
    }

    private void validateName(String name) {
        if (userDao.hasDuplicateName(name)) {
            throw new UserException(DUPLICATE_NAME);
        }
    }

}