package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurant.GetRestaurantResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.dto.restaurant.PostRestaurantResponse;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_STATUS;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("")
    public BaseResponse<PostRestaurantResponse> enroll(@RequestBody PostRestaurantRequest postRestaurantRequest) {
        return new BaseResponse<>(restaurantService.enroll(postRestaurantRequest));
    }

    @GetMapping("")
    public BaseResponse<List<GetRestaurantResponse>> getRestaurants(
            @RequestParam(required = false, defaultValue = "") String phone,
            @RequestParam(required = false, defaultValue = "") String name) {
        return new BaseResponse<>(restaurantService.getRestaurants(name, phone));
    }
}
