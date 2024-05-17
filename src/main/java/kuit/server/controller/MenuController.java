package kuit.server.controller;

import kuit.server.common.exception.MenuException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_MENU_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/menus")
public class MenuController {

  private final MenuService menuService;

  /**
   * 메뉴 신규 등록
   */
  @PostMapping("")
  public BaseResponse<Object> register(@PathVariable long storeId, @Validated @RequestBody PostMenuRequest request, BindingResult bindingResult) {
    log.info("[menuController.register]");
    if (bindingResult.hasErrors()) {
      throw new MenuException(INVALID_MENU_VALUE, getErrorMessages(bindingResult));
    }
    menuService.register(storeId, request);
    return new BaseResponse<>(null);
  }

  /**
   * 메뉴 상세정보 조회
   */
  @GetMapping("/{menuId}")
  public BaseResponse<GetMenuResponse> getMenuInfo(@PathVariable long storeId, @PathVariable long menuId) {
    log.info("[MenuController.getMenuInfo]");
    return new BaseResponse<>(menuService.getMenuInfo(storeId, menuId));
  }

  /**
   * 메뉴 정보 수정
   */
  @PatchMapping("/{menuId}")
  public BaseResponse<Object> update(@PathVariable long storeId, @PathVariable long menuId, @Validated @RequestBody PostMenuRequest request, BindingResult bindingResult) {
    log.info("[MenuController.update]");
    if (bindingResult.hasErrors()) {
      throw new MenuException(INVALID_MENU_VALUE, getErrorMessages(bindingResult));
    }
    menuService.update(menuId, request);
    return new BaseResponse<>(null);
  }

  /**
   * 메뉴 리스트 조회
   */
  @GetMapping("")
  public BaseResponse<List<GetMenuResponse>> getMenuList(@PathVariable long storeId) {
    log.info("[MenuController.getMenuList]");
    return new BaseResponse<>(menuService.getMenuList(storeId));
  }
}