package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurant.RestaurantMenuResponse;
import kuit.server.dto.restaurant.RestaurantOrderResponse;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    // 메뉴 삭제하기

}
