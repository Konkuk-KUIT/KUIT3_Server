package kuit.server.controller;

import kuit.server.common.exception.RestaurantException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.restaurant.*;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_RESTAURANT_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    /*
    신규 식당 등록
     */
    @PostMapping
    public BaseResponse<PostRestaurantResponse> register(
            @Validated @RequestBody PostRestaurantRequest postRestaurantRequest,
            BindingResult bindingResult){
        log.info("[RestaurantController.register]");

        if(bindingResult.hasErrors()){
            throw new RestaurantException(INVALID_RESTAURANT_VALUE, getErrorMessages(bindingResult));
        }

        PostRestaurantResponse result = restaurantService.createRestaurant(postRestaurantRequest);
        return new BaseResponse<PostRestaurantResponse>(result);
    }

    /*
    특정 종류의 식당 조회
     */
    @GetMapping("categories/{categoryId}")
    public BaseResponse<List<GetRestaurantResponse>> getRestaurants(@PathVariable int categoryId){
        return new BaseResponse<List<GetRestaurantResponse>>(restaurantService.findRestaurantsByCategory(categoryId));
    }

    /*
    식당에서 메뉴 추가
    */
    @PostMapping("/{restaurantId}/menus")
    public BaseResponse<Object> addMenu(@PathVariable long restaurantId,
                                        @RequestBody PostRestaurantMenuRequest postRestaurantMenuRequest){
        return new BaseResponse<>(restaurantService.addMenu(restaurantId, postRestaurantMenuRequest));
    }

    /*
    식당 메뉴 조회
     */
    @GetMapping("/{restaurantId}/menus")
    public BaseResponse<List<GetMenuResponse>> getMenus(@PathVariable long restaurantId){
        return new BaseResponse<>(restaurantService.getMenus(restaurantId));
    }

    /*
    식당 상태 변경
     */
    @PatchMapping("/{restaurantId}/closed")
    public BaseResponse<Object> modifyStatusAsClosed(@PathVariable Long restaurantId){
        restaurantService.modifyStatusAsClosed(restaurantId);
        return new BaseResponse<>(null);
    }

    /*
    식당 영업시간 문구 수정
     */
    @PatchMapping("/{restaurantId}/businesshour")
    public BaseResponse<Object> modifyBusinnessHour(@PathVariable Long restaurantId,
                                                    @RequestBody PatchBusinessHourRequest patchBusinessHourRequest){
        log.info("[RestaurantController.modifyBusinnessHour]");
        restaurantService.modifyBusinnessHour(restaurantId, patchBusinessHourRequest.getBusiness_hour());
        return new BaseResponse<>(null);
    }

    /*
    필터링 조건으로 식당 검색
     */
    @GetMapping("")
    public BaseResponse<List<GetRestaurantResponse>> searchRestraurants(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "min_star", required = false) Integer min_star,
            @RequestParam(value = "max_delivery_fee", required = false) String max_delivery_fee
    ){
        return new BaseResponse<>(restaurantService.search(keyword, min_star, max_delivery_fee));
    }

}
