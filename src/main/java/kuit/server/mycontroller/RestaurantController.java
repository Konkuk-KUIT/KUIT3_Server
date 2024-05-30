package kuit.server.mycontroller;

import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseResponse;
import kuit.server.mydto.retaurant.GetCategorizedRestaurantResp;
import kuit.server.mydto.retaurant.GetCategoryResp;
import kuit.server.mydto.retaurant.RestaurantReq;
import kuit.server.mydto.retaurant.RestaurantResp;
import kuit.server.mydto.retaurant.PageCondition;
import kuit.server.mydto.retaurant.menu.PostMenuReq;
import kuit.server.mydto.retaurant.menu.PostMenuResp;
import kuit.server.mydto.retaurant.menu.RestaurantMenuResp;
import kuit.server.myservice.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_RESTAURANT_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("")
    public BaseResponse<RestaurantResp> enroll(@Validated @RequestBody RestaurantReq restaurantReq, BindingResult bindingResult) {
        log.info("RestaurantController.enroll");
        if(bindingResult.hasErrors()) {
            throw new UserException(INVALID_RESTAURANT_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(restaurantService.enroll(restaurantReq));
    }

    @GetMapping("/categories")
    public BaseResponse<List<GetCategoryResp>> getCategories() {
        log.info("RestaurantController.getCategories");
        return new BaseResponse<>(restaurantService.getCategories());
    }

    @GetMapping("/{restaurant_PK}/foods")
    public BaseResponse<List<RestaurantMenuResp>> getRestaurantFoods(@PathVariable long restaurant_PK) {
        log.info("RestaurantController.getRestaurantFoods");
        return new BaseResponse<>(restaurantService.getRestaurantFoods(restaurant_PK));
    }

    @GetMapping("/{category}")
    public BaseResponse<GetCategorizedRestaurantResp> getCategorizedRestaurants(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int lastId,
            @RequestParam(required = false, defaultValue = "desc") String sortDirectionBy,
            @RequestParam(required = false) long numSortBy
    ) {
        log.info("RestaurantController.getCategorizedRestaurants");
        PageCondition pageCondition = new PageCondition(lastId, sortDirectionBy, numSortBy);
        return new BaseResponse<>(restaurantService.getCategorizedRestaurants(category, pageCondition));
    }

    @PostMapping("/{restaurant_PK}/addMenu")
    public BaseResponse<PostMenuResp> addMenu(@PathVariable long restaurant_PK, @Validated @RequestBody PostMenuReq postMenuReq, BindingResult bindingResult) {
        log.info("RestaurantController.addMenu");
        if(bindingResult.hasErrors()) {
            throw new UserException(INVALID_RESTAURANT_VALUE);
        }

        return new BaseResponse<>(restaurantService.addMenu(restaurant_PK ,postMenuReq));
    }


}
