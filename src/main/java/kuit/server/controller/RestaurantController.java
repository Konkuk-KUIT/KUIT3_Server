package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.restaurant.*;
import kuit.server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BaseResponse<PostRestaurantResponse> register(@RequestBody PostRestaurantRequest postRestaurantRequest){
        log.info("[RestaurantController.register]");
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
    * */
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
    @PatchMapping("/{restaurantId}")
    public BaseResponse<Object> modifyBusinnessHour(@PathVariable Long restaurantId,
                                                    @RequestBody PatchBusinessHourRequest patchBusinessHourRequest){
        log.info("[RestaurantController.modifyBusinnessHour]");
        restaurantService.modifyBusinnessHour(restaurantId, patchBusinessHourRequest.getBusiness_hour());
        return new BaseResponse<>(null);
    }

}
