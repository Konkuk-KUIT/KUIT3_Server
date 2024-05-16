package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.member.response.GetMemberResponse;
import kuit.server.dto.member.request.PostMemberRequest;
import kuit.server.dto.member.response.PostMemberResponse;
import kuit.server.dto.user.PatchNicknameRequest;
import kuit.server.dto.user.PostUserResponse;
import kuit.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
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

    /**
     * 회원 가입
     */
    @PostMapping("")
    public BaseResponse<PostMemberResponse> signUp(@Validated @RequestBody PostMemberRequest postMemberRequest, BindingResult bindingResult) {
        log.info("[UserController.signUp]");
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(memberService.createMemberByPostMemberResponse(postMemberRequest));
    }

    /**
     * 회원 닉네임 변경
     **/
    @PatchMapping("/{memberId}/nickname")
    public BaseResponse<String> changeNickname(@RequestBody Map<String,String> requestMap, @PathVariable Long memberId) {
        log.info("[UserController.changeNickname]");
        return new BaseResponse<>(memberService.changeNickname(memberId,requestMap.get("nickname")));

    }

    /**
     * 회원 정보 전부 수정
     */
    @PutMapping("/{memberId}")
    public BaseResponse<String> changeAll(@RequestBody PostMemberRequest postMemberRequest, @PathVariable Long memberId) {
        log.info("[UserController.change]");
        return new BaseResponse<>(memberService.changeAll(memberId,postMemberRequest));
    }
}
