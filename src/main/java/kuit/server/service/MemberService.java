package kuit.server.service;

import kuit.server.common.exception.UserException;
import kuit.server.dao.MemberDao;
import kuit.server.dao.UserDao;
import kuit.server.domain.Member;
import kuit.server.dto.member.request.PostMemberRequest;
import kuit.server.dto.member.response.GetMemberResponse;
import kuit.server.dto.member.response.PostMemberResponse;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.dto.user.PostUserResponse;
import kuit.server.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DUPLICATE_EMAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    public Member findOneById(Long id){
        log.info("[UserService.findOneById]");
        return memberDao.findById(id);
    }

    public GetMemberResponse findMemberResponseById(Long id){
        log.info("[UserService.findMemberResponseById]");
        return GetMemberResponse.of(findOneById(id));
    }

    public Member createMember(Member member) {
        log.info("[UserService.member]");
        memberDao.createMember(member);
        return member;


    }
    public PostMemberResponse createMemberByPostMemberResponse(PostMemberRequest postMemberRequest) {
        log.info("[UserService.createMember]");
        Member member = createMember(postMemberRequest.toEntity());
        return PostMemberResponse.of(member);
    }

    /*private void validateEmail(String email) {
        if (userDao.hasDuplicateEmail(email)) {
            throw new UserException(DUPLICATE_EMAIL);
        }
    }*/
}
