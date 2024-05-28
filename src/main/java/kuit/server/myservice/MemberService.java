package kuit.server.myservice;

import kuit.server.common.exception.DatabaseException;
import kuit.server.common.exception.UserException;
import kuit.server.mydao.MemberDao;
import kuit.server.mydto.member.*;
import kuit.server.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Service
@Transactional(rollbackFor = UserException.class)
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String updatePassword(long userId, PatchPasswordReq patchPasswordReq) {
        log.info("MemberService.updatePassword");
        int result = memberDao.updateUserPassword(userId, patchPasswordReq.getPassword());
        if(result != 1) {
            throw new DatabaseException(DATABASE_ERROR);
        }
        return "complete changing password";
    }

    public String updateEmail(long userId, PatchEmailReq patchEmailReq) {
        log.info("MemberService.updateEmail");
        validateEmail(patchEmailReq.getEmail());
        int result = memberDao.updateUserEmail(userId, patchEmailReq.getEmail());
        if(result != 1) {
            throw new UserException(EMAIL_NOT_FOUND);
        }
        return "complete changing Email";
    }

    public PostLoginResp logIn(PostLoginReq loginRequest) {
        log.info("MemberService.logIn");

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        long userId;
        try {
            userId = memberDao.getUserIdByEmail(email);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new UserException(EMAIL_NOT_FOUND);
        }
        validatePassword(password, userId);
        String jwt = "12121";
        return new PostLoginResp(userId, jwt);
    }

    public PostMemberResp signUp(PostMemberReq postMemberReq) {
        log.info("MemberService.signUp");

        // FIXME : DTO에 Nullable로 설정한 부분만 null인지 확인하기, 검증하기
        if(postMemberReq.getNickName() != null) {
            validateNickName(postMemberReq.getNickName());
        }
        validateEmail(postMemberReq.getEmail());
        // FIXME : jwt 및 비번 인코딩
        String encodedPassword = passwordEncoder.encode(postMemberReq.getPassword());
        postMemberReq.setPassword(encodedPassword);
        long userId = memberDao.createMember(postMemberReq);
        String jwt = jwtTokenProvider.createToken(postMemberReq.getEmail(), userId);
        return new PostMemberResp(userId, jwt);
    }

    private void validatePassword(String password, long userId) {
        String DBpassword = memberDao.getPasswordByUserId(userId);
        if(!Objects.equals(password, DBpassword)) {
            throw new UserException(PASSWORD_NO_MATCH);
        }
    }

    private void validateNickName(String nickName) {
        if (memberDao.hasDuplicateNickName(nickName)) {
            throw new UserException(DUPLICATE_NICKNAME);
        }
    }

    private void validateEmail(String email) {
        if (memberDao.hasDuplicateEmail(email)) {
            throw new UserException(DUPLICATE_EMAIL);
        }
    }

    public String updateAllInfo(long userId, PostMemberReq postMemberReq) {
        log.info("MemberService.updateAllInfo");
        validateNickName(postMemberReq.getNickName());
        validateEmail(postMemberReq.getEmail());
        if (memberDao.changeAll(userId, postMemberReq) != 1) {
            throw new DatabaseException(DATABASE_ERROR);
        }
        return "complete changing user Info";
    }

    public List<GetUserInfoResp> findUser(String nickName, String email) {
        log.info("MemberService.findUser");
        try {
            return memberDao.findUserInfo(nickName, email);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new UserException(USER_NOT_FOUND);
        }
    }
}
