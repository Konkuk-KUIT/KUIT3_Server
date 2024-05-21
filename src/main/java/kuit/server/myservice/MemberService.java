package kuit.server.myservice;

import kuit.server.common.exception.UserException;
import kuit.server.mydao.MemberDao;
import kuit.server.mydto.member.PostLoginReq;
import kuit.server.mydto.member.PostLoginResp;
import kuit.server.mydto.member.PostMemberReq;
import kuit.server.mydto.member.PostMemberResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;

    public String updatePassword(long userId, PostMemberReq postMemberReq) {
        log.info("MemberService.updatePassword");
        int result = memberDao.updateUserPassword(userId, postMemberReq.getPassword());
        if(result == 1) {
            return "complete changing password";
        }
        return "failed to change password";
    }

    public String updateEmail(long userId, PostMemberReq postMemberReq) {
        log.info("MemberService.updateEmail");
        int result = memberDao.updateUserEmail(userId, postMemberReq.getEmail());
        if(result == 1) {
            return "complete changing Email";
        }
        return "failed to change Email";
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

    private void validatePassword(String password, long userId) {
        String DBpassword = memberDao.getPasswordByUserId(userId);
        if(!passwordEncoder.matches(DBpassword, password)) {
            throw new UserException(PASSWORD_NO_MATCH);
        }
    }

    public PostMemberResp signUp(PostMemberReq postMemberReq) {
        log.info("MemberService.signUp");

        if(postMemberReq.getNickName() != null) {
            validateNickName(postMemberReq.getNickName());
        }
        validateEmail(postMemberReq.getEmail());

        long userId = memberDao.createMember(postMemberReq);
        String jwt = "1212";
        return new PostMemberResp(userId, jwt);
    }

    private void validateNickName(String nickname) {
        if (memberDao.hasDuplicateNickName(nickname)) {
            throw new UserException(DUPLICATE_NICKNAME);
        }
    }

    private void validateEmail(String email) {
        if (memberDao.hasDuplicateEmail(email)) {
            throw new UserException(DUPLICATE_EMAIL);
        }
    }
}
