package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.PatchNicknameRequest;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.dto.user.PostUserResponse;
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
   * 회원 상세정보 조회
   */
  @GetMapping("/{userId}")
  public BaseResponse<GetUserResponse> getUserInfo(@PathVariable long userId) {
    log.info("[UserController.getUserInfo]");
    return new BaseResponse<>(userService.getUserInfo(userId));
  }

  /**
   * 회원 상태 변경
   */
  @PatchMapping("/{userId}/{status}")
  public BaseResponse<Object> modifyUserStatus_active(@PathVariable long userId, @PathVariable String status) {
    log.info("[UserController.modifyUserStatus]");
    userService.modifyUserStatus(userId, status);
    return new BaseResponse<>(null);
  }

  /**
   * 닉네임 변경
   */
  @PatchMapping("/{userId}/nickname")
  public BaseResponse<String> modifyNickname(@PathVariable long userId,
                                             @Validated @RequestBody PatchNicknameRequest patchNicknameRequest, BindingResult bindingResult) {
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
}