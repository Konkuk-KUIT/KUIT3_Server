package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.controller.validator.PostMemberRequestValidator;
import kuit.server.dto.member.response.GetMemberResponse;
import kuit.server.dto.member.request.PostMemberRequest;
import kuit.server.dto.member.response.PostMemberResponse;
import kuit.server.dto.user.PatchNicknameRequest;
import kuit.server.dto.user.PostUserResponse;
import kuit.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PostMemberRequestValidator postMemberRequestValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        //log.info("init binder {}",webDataBinder);
        //webDataBinder.addValidators(postMemberRequestValidator);
    }
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
        log.info("[MemberController.signUp]");

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
        log.info("[MemberController.changeNickname]");
        return new BaseResponse<>(memberService.changeNickname(memberId,requestMap.get("nickname")));

    }

    /**
     * 회원 정보 전부 수정
     */
    @PutMapping("/{memberId}")
    public BaseResponse<String> changeAll(@RequestBody PostMemberRequest postMemberRequest, @PathVariable Long memberId) {
        log.info("[MemberController.change]");
        return new BaseResponse<>(memberService.changeAll(memberId,postMemberRequest));
    }

    /**
     * 회원 전부 조회
     */
    @GetMapping()
    public BaseResponse<List<GetMemberResponse>> getUsers() {
        log.info("[MemberController.getUserById]");
        return new BaseResponse<>(memberService.findMemberResponses());

    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/{memberId}")
    public BaseResponse<String> deleteMember(@PathVariable long memberId) {
        log.info("[MemberController.deleteMember]");
        return new BaseResponse<>(memberService.deleteById(memberId));
    }
}
