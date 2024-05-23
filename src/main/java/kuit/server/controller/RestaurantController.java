package kuit.server.controller;

import kuit.server.common.exception.RestaurantException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurant.MenuUpdateRequest;
import kuit.server.dto.restaurant.RestaurantMenuRequest;
import kuit.server.dto.restaurant.RestaurantMenuResponse;
import kuit.server.dto.restaurant.RestaurantOrderResponse;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_MENU_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 주문 내역 가져오기
    @GetMapping("/{restaurantId}/orders")
    public BaseResponse<List<RestaurantOrderResponse>> getRestaurantOrders(@PathVariable Long restaurantId){
        return new BaseResponse<>(restaurantService.getOrders(restaurantId));
    }

    // 음식점 메뉴 가져오기
    @GetMapping("/{restaurantId}/menus")
    public BaseResponse<List<RestaurantMenuResponse>> getRestaurantMenu(@PathVariable Long restaurantId){
        return new BaseResponse<>(restaurantService.getMenu(restaurantId));
    }

    // 메뉴 등록하기
    @PostMapping("/{restaurantId}/menu")
    public BaseResponse<String> createMenu(@PathVariable Long restaurantId,
                                           @Validated @RequestBody RestaurantMenuRequest menuRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new RestaurantException(INVALID_MENU_VALUE, getErrorMessages(bindingResult));
        }
        restaurantService.createMenu(restaurantId,menuRequest);
        return new BaseResponse<>(null);
    }


    // 메뉴 삭제하기
    @PatchMapping("/{restaurantId}/menu/{menuId}/deleted")
    public BaseResponse<String> deleteMenu(@PathVariable Long restaurantId, @PathVariable Long menuId){
        restaurantService.modifyMenuStatus_delete(restaurantId,menuId);
        return new BaseResponse<>(null);
    }

    // 메뉴 수정하기
    @PutMapping("/{restaurantId}/menu/{menuId}/updated")
    public BaseResponse<String> updateMenu(@PathVariable Long restaurantId, @PathVariable Long menuId,
                                           @Validated @RequestBody MenuUpdateRequest menuUpdateRequest,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RestaurantException(INVALID_MENU_VALUE, getErrorMessages(bindingResult));
        }

        restaurantService.updateMenu(restaurantId,menuId,menuUpdateRequest);
        return new BaseResponse<>(null);
    }

    // 가게 폐업
    @PatchMapping("/{restaurantId}/deleted")
    public BaseResponse<String> deleteRestaurant(@PathVariable Long restaurantId){
        restaurantService.modifyStatus_delete(restaurantId);
        return new BaseResponse<>(null);
    }


}
