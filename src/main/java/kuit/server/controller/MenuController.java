package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.menu.PatchPriceRequest;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.menu.PostMenuResponse;
import kuit.server.dto.user.PatchNicknameRequest;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.dto.user.PostUserResponse;
import kuit.server.service.MenuService;
import kuit.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;
    @PostMapping("")
    public BaseResponse<PostMenuResponse> registerMenu(@Validated @RequestBody PostMenuRequest postMenuRequest, BindingResult bindingResult) {
        log.info("[MenuController.registerMenu]");
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(menuService.registerMenu(postMenuRequest));
    }
    @PatchMapping("/{menuId}/price")
    public BaseResponse<String> modifyPrice(@PathVariable long menuId,
                                            @Validated @RequestBody PatchPriceRequest patchPriceRequest, BindingResult bindingResult) {
        log.info("[MenuController.modifyPrice]");
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        menuService.modifyPrice(menuId, patchPriceRequest.getPrice());
        return new BaseResponse<>(null);
    }

}
