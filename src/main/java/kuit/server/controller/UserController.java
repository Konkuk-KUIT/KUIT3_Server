package kuit.server.controller;

import jakarta.servlet.http.HttpServletRequest;
import kuit.server.common.argument_resolver.PreAuthorize;
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
     * 회원 휴면
     */
    @PatchMapping("/dormant")
    public BaseResponse<Object> modifyUserStatus_dormant(HttpServletRequest request) {
        Object userIdAttribute = request.getAttribute("userId");
        if (userIdAttribute == null) {
            throw new IllegalStateException("userId 속성이 요청에 없습니다.");
        }
        long userId = (Long) userIdAttribute;
        log.info("[UserController.modifyUserStatus_dormant] UserId: {}", userId);
        userService.modifyUserStatus_dormant(userId);
        return new BaseResponse<>(null);
    }

    /**
     * 회원 탈퇴
     */
    @PatchMapping("/deleted")
    public BaseResponse<Object> modifyUserStatus_deleted(HttpServletRequest request) {
        Object userIdAttribute = request.getAttribute("userId");
        if (userIdAttribute == null) {
            throw new IllegalStateException("userId 속성이 요청에 없습니다.");
        }
        long userId = (Long) userIdAttribute;
        log.info("[UserController.modifyUserStatus_delete] UserId: {}", userId);
        userService.modifyUserStatus_deleted(userId);
        return new BaseResponse<>(null);
    }

    /**
     * 닉네임 변경
     */
    @PatchMapping("/nickname")
    public BaseResponse<String> modifyNickname(@Validated @RequestBody PatchNicknameRequest patchNicknameRequest,
                                               BindingResult bindingResult,
                                               @PreAuthorize long userId) {
        log.info("[UserController.modifyNickname]");

        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        userService.modifyNickname(userId, patchNicknameRequest.getNickname());
        return new BaseResponse<>(null);
    }

    /**
     * 회원 목록 조회
     */
    @GetMapping("")
    public BaseResponse<List<GetUserResponse>> getUsers(
            @RequestParam(required = false, defaultValue = "") String nickname,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "active") String status) {
        log.info("[UserController.getUsers]");
        if (!status.equals("active") && !status.equals("dormant") && !status.equals("deleted")) {
            throw new UserException(INVALID_USER_STATUS);
        }
        return new BaseResponse<>(userService.getUsers(nickname, email, status));
    }
    /**
     * 회원 조회
     */
    @GetMapping("/{userId}")
    public BaseResponse<GetUserResponse> getUserById(@PathVariable long userId) {
        log.info("[UserController.getUserById] userId: {}", userId);
        return new BaseResponse<>(userService.getUserById(userId));
    }
}