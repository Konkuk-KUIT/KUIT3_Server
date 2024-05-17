package kuit.server.mycontroller;

import kuit.server.common.response.BaseResponse;
import kuit.server.mydto.member.PostLoginReq;
import kuit.server.mydto.member.PostLoginResp;
import kuit.server.mydto.member.PostMemberReq;
import kuit.server.mydto.member.PostMemberResp;
import kuit.server.myservice.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public BaseResponse<PostMemberResp> singUp(@RequestBody PostMemberReq postMemberReq) {
        log.info("MemberController.signUp");
        return new BaseResponse<>(memberService.signUp(postMemberReq));
    }

    @PostMapping("/login")
    public BaseResponse<PostLoginResp> logIn(@RequestBody PostLoginReq postLoginReq) {
        log.info("MemberController.login");
        return new BaseResponse<>(memberService.logIn(postLoginReq));
    }

    @PatchMapping("/{userId}/email")
    public BaseResponse<String> updateEmail(@PathVariable long userId, @RequestBody PostMemberReq postMemberReq) {
        log.info("MemberController.updateEmail");
        return new BaseResponse<>(memberService.updateEmail(userId, postMemberReq));
    }

    @PatchMapping("/{userId}/password")
    public BaseResponse<String> updatePassword(@PathVariable long userId, @RequestBody PostMemberReq postMemberReq) {
        log.info("MemberController.updatePassword");
        return new BaseResponse<>(memberService.updatePassword(userId, postMemberReq));
    }
}
