package kuit.server.controller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.restaurant.GetRestaurantResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_RESTAURANT_STATUS;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_RESTAURANT_VALUE;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.DUPLICATE_PHONE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * 레스토랑 등록
     */
    @PostMapping("")
    public BaseResponse<Long> registerRestaurant(@Validated @RequestBody PostRestaurantRequest postRestaurantRequest, BindingResult bindingResult) {
        log.info("[RestaurantController.registerRestaurant]");
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_RESTAURANT_VALUE, getErrorMessages(bindingResult));
        }
        long restaurantId = restaurantService.registerRestaurant(postRestaurantRequest);
        return new BaseResponse<>(restaurantId);
    }

    /**
     * 레스토랑 상태 수정
     */
    @PatchMapping("/{restaurantid}/status")
    public BaseResponse<Object> modifyRestaurantStatus(@PathVariable long restaurantid, @RequestParam String status) {
        log.info("[RestaurantController.modifyRestaurantStatus]");
        restaurantService.updateRestaurantStatus(restaurantid, status);
        return new BaseResponse<>(null);
    }

    /**
     * 레스토랑 정보 수정
     */
    @PutMapping("/{restaurantid}")
    public BaseResponse<Object> modifyRestaurantDetails(@PathVariable long restaurantid,
                                                        @Validated @RequestBody PostRestaurantRequest postRestaurantRequest,
                                                        BindingResult bindingResult) {
        log.info("[RestaurantController.modifyRestaurantDetails]");
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_RESTAURANT_VALUE, getErrorMessages(bindingResult));
        }
        restaurantService.updateRestaurantDetails(restaurantid, postRestaurantRequest);
        return new BaseResponse<>(null);
    }

    /**
     * 레스토랑 목록 조회
     */
    @GetMapping("")
    public BaseResponse<List<GetRestaurantResponse>> getRestaurants(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String location,
            @RequestParam(required = false, defaultValue = "Open") String status) {
        log.info("[RestaurantController.getRestaurants]");
        if (!status.equals("Close") && !status.equals("Open")) {
            throw new UserException(INVALID_RESTAURANT_STATUS);
        }
        return new BaseResponse<>(restaurantService.getRestaurants(name, location, status));
    }

    /**
     * 레스토랑 ID로 레스토랑 조회
     */
    @GetMapping("/{restaurantid}")
    public BaseResponse<GetRestaurantResponse> getRestaurantById(@PathVariable long restaurantid) {
        log.info("[RestaurantController.getRestaurantById]");
        return new BaseResponse<>(restaurantService.getRestaurantById(restaurantid));
    }
}
