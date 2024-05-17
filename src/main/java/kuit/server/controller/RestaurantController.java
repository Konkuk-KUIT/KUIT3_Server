package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurant.GetCategoryResponse;
import kuit.server.dto.restaurant.GetStoreResponse;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_STATUS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @RequestMapping("")
    public BaseResponse<List<GetStoreResponse>> getStores() {
        log.info("[RestaurantController.getStores]");
        return new BaseResponse<>(restaurantService.getAllStores());
    }
    @RequestMapping("/sortedstores")
    public BaseResponse<List<GetStoreResponse>> getStoresSortedByMinimumPrice(@RequestParam(required = true) Integer minimumPrice) {
        log.info("[RestaurantController.getStoresSortedByMinimumPrice]");
        return new BaseResponse<>(restaurantService.getStoresSortedByMinimumPrice(minimumPrice));
    }
    @RequestMapping("/categories")
    public BaseResponse<List<GetCategoryResponse>> getCategories() {
        log.info("[RestaurantController.getCategories]");
        return new BaseResponse<>(restaurantService.getRestaurants());
    }

    @RequestMapping("/{categoryId}")
    public BaseResponse<List<GetStoreResponse>> getSpecificCategories(@PathVariable("categoryId") long categoryId) {
        log.info("[RestaurantController.getSpecificCategories]");
        return new BaseResponse<>(restaurantService.getSpecificCategories(categoryId));
    }

}
