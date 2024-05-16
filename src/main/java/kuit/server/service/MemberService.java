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

import java.util.List;
import java.util.stream.Collectors;

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
        log.info("[UserService.createMemberByPostMemberResponse]");
        Member member = createMember(postMemberRequest.toEntity());
        return PostMemberResponse.of(member);
    }

    public String changeNickname(Long id,String nickname){
        log.info("[UserService.changeNickname]");
        if(memberDao.modifyNickname(id,nickname)==1)
            return "success";
        return "fail";

    }

    public String changeAll(Long id,PostMemberRequest postMemberRequest){
        log.info("[UserService.changeAll]");
        postMemberRequest.setMemberId(id);
        Member member=postMemberRequest.toEntity();
        if(memberDao.modifyAll(id,member)==1)
            return "success";
        return "fail";
    }

    public List<Member> findAll(){
        log.info("[UserService.findAll]");
        return memberDao.findAll();
    }

    public List<GetMemberResponse>findMemberResponses(){
        log.info("[UserService.findMemberResponses]");
        return findAll().stream()
                .map(GetMemberResponse::of)
                .collect(Collectors.toList());
    }
}
