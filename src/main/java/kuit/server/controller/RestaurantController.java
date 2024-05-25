package kuit.server.controller;

import kuit.server.common.exception.RestaurantException;
import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurants.GetRestaurantResponse;
import kuit.server.dto.restaurants.PostRestaurantRequest;
import kuit.server.dto.restaurants.PostRestaurantResponse;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.dto.user.PostUserResponse;
import kuit.server.service.RestaurantService;
import kuit.server.util.BindingResultUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final PostRestaurantRequest postRestaurantRequest;

    /**
     * 식당 목록 조회
     */
    @GetMapping("")
    public BaseResponse<List<GetRestaurantResponse>> getRestaurants(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "open") String status) {
        log.info("[RestaurantController.getRestaurants]");

        // Check if status is null or invalid
        if (status == null || (!status.equals("open") && !status.equals("deleted") && !status.equals("active"))) {
            throw new RestaurantException(INVALID_RESTAURANT_STATUS);
        }

        return new BaseResponse<>(restaurantService.getRestaurants(name, category, status));
    }

    /**
     * 식당 추가
     */
    @PostMapping("")
    public BaseResponse<PostRestaurantResponse> newRestaurant(@Validated @RequestBody PostRestaurantRequest postRRequest, BindingResult bindingResult) {
        log.info("[RestaurantController.newRestaurant]");

        if (bindingResult.hasErrors()) {
            throw new RestaurantException(INVALID_RESTAURANT_VALUE, BindingResultUtils.getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(RestaurantService.newRestaurant(postRestaurantRequest));
    }
}
