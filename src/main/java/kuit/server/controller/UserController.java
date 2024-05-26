package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.user.*;
import kuit.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_STATUS;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입
     */
    @PostMapping("")
    public BaseResponse<PostUserResponse> signUp(@Validated @RequestBody PostUserRequest postUserRequest, BindingResult bindingResult) {
        log.info("[UserController.signUp]");

        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(userService.signUp(postUserRequest));
    }

    /**
     * 회원 목록 조회
     */
    @GetMapping("")
    public BaseResponse<List<GetUserResponse>> getUsers(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "Active") String status) {
        log.info("[UserController.getUsers]");
        if (!status.equals("Active") && !status.equals("Inactive")) {
            throw new UserException(INVALID_USER_STATUS);
        }
        return new BaseResponse<>(userService.getUsers(name, email, status));
    }

    /**
     * 회원 탈퇴
     */
    @PatchMapping("/{userId}/inactive")
    public BaseResponse<Object> modifyUserStatus_inactive(@PathVariable long userId) {
        log.info("[UserController.modifyUserStatus_inactive]");
        userService.modifyUserStatus_inactive(userId);
        return new BaseResponse<>(null);
    }

    /**
     * 이름 변경
     */
    @PatchMapping("/{userId}/name")
    public BaseResponse<String> modifyName(@PathVariable long userId,
                                           @Validated @RequestBody PatchNameRequest patchNameRequest,
                                           BindingResult bindingResult) {

        log.info("[UserController.,modifyName]");

        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }

        userService.modifyName(userId, patchNameRequest.getName());
        return new BaseResponse<>(null);
    }

    /**
     * 이메일로 조회한 회원 정보 수정
     */

    @PutMapping("/{email}")
    public BaseResponse<String> updateUserByEmail(@PathVariable String email,
                                                  @Validated @RequestBody PostUserRequest postUserRequest,
                                                  BindingResult bindingResult) {
        log.info("[UserController.updateUserByEmail]");

        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }

        userService.updateUserByEmail(email, postUserRequest);
        return new BaseResponse<>(null);

    }
}