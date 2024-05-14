package kuit.server.controller;


import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurant.GetCategoryResponse;
import kuit.server.dto.restaurant.GetRestaurantResponse;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * 식당 목록 조회
     */
    @GetMapping("")
    public BaseResponse<List<GetRestaurantResponse>> getRestaurants() {
        return new BaseResponse<>(restaurantService.getRestaurants());
    }

    /**
     * 카테고리 조회
     */
    @GetMapping("/categories")
    public BaseResponse<List<GetCategoryResponse>> getCategories() {
        return new BaseResponse<>(restaurantService.getCategories());
    }

    /**
     * 카테고리별 식당 목록 조회
     */

    @GetMapping("/{categoryId}")
    public BaseResponse<List<GetRestaurantResponse>> getRestaurantsByCategory(@PathVariable("categoryId") Long categoryId) {
        return new BaseResponse<>(restaurantService.getRestaurantsByCategory(categoryId));
    }

}
