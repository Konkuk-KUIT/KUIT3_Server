package kuit.server.mycontroller;

import com.fasterxml.jackson.databind.ser.Serializers;
import kuit.server.common.argument_resolver.PreAuthorize;
import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.mydto.member.*;
import kuit.server.myservice.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public BaseResponse<PostMemberResp> singUp(@Validated @RequestBody PostMemberReq postMemberReq, BindingResult bindingResult) {
        log.info("MemberController.signUp");

        if(bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(memberService.signUp(postMemberReq));
    }

    @PostMapping("/login")
    public BaseResponse<PostLoginResp> logIn(@Validated @RequestBody PostLoginReq postLoginReq, BindingResult bindingResult) {
        log.info("MemberController.login");
        if(bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(memberService.logIn(postLoginReq));
    }

    @PatchMapping("/email")
    public BaseResponse<String> updateEmail(@PreAuthorize long userId, @Validated @RequestBody PatchEmailReq patchEmailReq, BindingResult bindingResult) {
        log.info("MemberController.updateEmail");
        if(bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(memberService.updateEmail(userId, patchEmailReq));
    }

    @PatchMapping("/password")
    public BaseResponse<String> updatePassword(@PreAuthorize long userId, @Validated @RequestBody PatchPasswordReq patchPasswordReq, BindingResult bindingResult) {
        log.info("MemberController.updatePassword");
        if(bindingResult.hasErrors()) {
            throw new RuntimeException(getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(memberService.updatePassword(userId, patchPasswordReq));
    }

    @PutMapping("/updateAll")
    public BaseResponse<String> updateAllInfo(@PreAuthorize long userId, @Validated @RequestBody PostMemberReq postMemberReq, BindingResult bindingResult) {
        log.info("MemberController.updateAllInfo");
        if(bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(memberService.updateAllInfo(userId, postMemberReq));
    }

    @GetMapping("/userInfo")
    public BaseResponse<List<GetUserInfoResp>> findUser(@RequestParam("nickname") String nickName, @RequestParam("email") String email) {
        log.info("MemberController.findUser");
        return new BaseResponse<>(memberService.findUser(nickName, email));
    }
}
