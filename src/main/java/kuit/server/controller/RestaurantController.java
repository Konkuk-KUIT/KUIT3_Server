package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurant.GetRestaurant;
import kuit.server.dto.restaurant.GetcategoryResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    @GetMapping("/categories")
    public BaseResponse<List<GetcategoryResponse>> getCategory(@RequestParam(required = true,defaultValue = "")String category) {
        return new BaseResponse<>(restaurantService.getCatogories(category));
    }
    @GetMapping("")
    public BaseResponse<List<GetRestaurant>> getRestaurant() {
        return new BaseResponse<>(restaurantService.getRestaurants());
    }
    @PostMapping("")
    public BaseResponse<Long> createRestaurant(@Validated @RequestBody PostRestaurantRequest postRestaurantRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new UserException(INVALID_USER_VALUE);
        }
        long restaurantId = restaurantService.createRestaurant(postRestaurantRequest);
        return new BaseResponse<>(restaurantId);
    }
}
