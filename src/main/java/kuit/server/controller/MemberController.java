package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.member.GetMemberResponse;
import kuit.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 조회
     */
    @GetMapping("/{memberId}")
    public BaseResponse<GetMemberResponse> getUserById(
            @PathVariable long memberId) {
        log.info("[UserController.getUserById]");
        return new BaseResponse<>(memberService.findMemberResponseById(memberId));

    }
}
