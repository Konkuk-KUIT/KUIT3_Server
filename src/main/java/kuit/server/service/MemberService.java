package kuit.server.service;

import kuit.server.dao.UserDao;
import kuit.server.domain.Member;
import kuit.server.dto.member.GetMemberResponse;
import kuit.server.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    public Member findOneById(Long id){
        log.info("[UserService.findOneById]");
        return userDao.findById(id);
    }

    public GetMemberResponse findMemberResponseById(Long id){
        log.info("[UserService.findMemberResponseById]");
        return GetMemberResponse.of(findOneById(id));
    }

    
}
