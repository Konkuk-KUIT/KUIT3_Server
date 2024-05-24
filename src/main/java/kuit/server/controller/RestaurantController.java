package kuit.server.controller;

import kuit.server.common.exception.RestaurantException;
import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurant.GetRestaurant;
import kuit.server.dto.restaurant.GetcategoryResponse;
import kuit.server.dto.restaurant.PatchRestaurantNameRequest;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.dto.user.PatchNicknameRequest;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_RESTAURANT_VALUE;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    @GetMapping("")
    public BaseResponse<List<GetRestaurant>> getRestaurant(
            @RequestParam(required = false, defaultValue = "") String restaurantname,
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "active") String status
    ) {
        return new BaseResponse<>(restaurantService.getRestaurants(restaurantname,category,status));
    }
    @PostMapping("")
    public BaseResponse<Long> createRestaurant(@Validated @RequestBody PostRestaurantRequest postRestaurantRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RestaurantException(INVALID_RESTAURANT_VALUE,getErrorMessages(bindingResult));
        }
        long restaurantId = restaurantService.createRestaurant(postRestaurantRequest);
        return new BaseResponse<>(restaurantId);
    }
    @PatchMapping("/{restaurantId}/restaurantName")
    public BaseResponse<String> modifyNickname(@PathVariable long restaurantId,
                                               @Validated @RequestBody PatchRestaurantNameRequest patchRestaurantNameRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RestaurantException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        restaurantService.modifyRestaurantName(restaurantId, patchRestaurantNameRequest.getRestaurantname());
        return new BaseResponse<>(null);
    }
}
