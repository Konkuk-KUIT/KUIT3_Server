package kuit.server.mycontroller;

import kuit.server.common.response.BaseResponse;
import kuit.server.mydto.retaurant.RestaurantReq;
import kuit.server.mydto.retaurant.RestaurantResp;
import kuit.server.mydto.retaurant.menu.RestaurantMenuResp;
import kuit.server.myservice.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("")
    public BaseResponse<RestaurantResp> enroll(@RequestBody RestaurantReq restaurantReq) {
        log.info("RestaurantController.enroll");
        return new BaseResponse<>(restaurantService.enroll(restaurantReq));
    }

    @GetMapping("/{restaurant_PK}/foods")
    public BaseResponse<List<RestaurantMenuResp>> getRestaurantFoods(@PathVariable long restaurant_PK) {
        return new BaseResponse<>(restaurantService.getRestaurantFoods(restaurant_PK));
    }
}
