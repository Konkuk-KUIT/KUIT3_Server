package kuit.server.controller;

import kuit.server.common.exception.MenuException;
import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PatchPriceRequest;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.menu.PostMenuResponse;
import kuit.server.dto.user.GetUserResponse;
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

import java.util.List;

import static kuit.server.util.BindingResultUtils.getErrorMessages;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_MENU_VALUE;

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
            throw new MenuException(INVALID_MENU_VALUE, getErrorMessages(bindingResult));
        }

        return new BaseResponse<>(menuService.registerMenu(postMenuRequest));
    }
    @PatchMapping("/{menuId}/price")
    public BaseResponse<String> modifyPrice(@PathVariable long menuId,
                                            @Validated @RequestBody PatchPriceRequest patchPriceRequest, BindingResult bindingResult) {
        log.info("[MenuController.modifyPrice]");
        if (bindingResult.hasErrors()) {
            throw new MenuException(INVALID_MENU_VALUE, getErrorMessages(bindingResult));
        }
        menuService.modifyPrice(menuId, patchPriceRequest.getPrice());
        return new BaseResponse<>(null);
    }
    @GetMapping("")
    public BaseResponse<List<GetMenuResponse>> getMenus(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "") String price) {
        log.info("[MenuController.getMenus]");

        return new BaseResponse<>(menuService.getMenus(name, category, price));
    }

    @PatchMapping("/{menuId}/deleted")
    public BaseResponse<Object> modifyMenuStatus_deleted(@PathVariable long menuId) {
        log.info("[MenuController.modifyMenuStatus_deleted]");
        menuService.modifyMenuStatus_deleted(menuId);
        return new BaseResponse<>(null);
    }

}
