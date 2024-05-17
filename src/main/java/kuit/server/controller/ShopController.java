package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.shop.FoodCategory;
import kuit.server.dto.shop.Shop;
import kuit.server.dto.user.PostUserResponse;
import kuit.server.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;


    @GetMapping("/list")
    public BaseResponse<List<Shop>> getShops(@RequestParam(required = false) String category, @RequestParam(required = false) String address) {
        if (category != null && address != null) {
            // 카테고리와 주소로 가게 검색
            return new BaseResponse<>(shopService.getShopsByCategoryAndAddress(category, address));

        } else if (category != null) {
            // 카테고리로 가게 검색
            return new BaseResponse<>(shopService.getShopsByCategory(category));
        } else if (address != null) {
            // 주소로 가게 검색
            return new BaseResponse<>(shopService.getShopsByAddress(address));
        } else {
            // 카테고리와 주소가 주어지지 않은 경우 모든 가게 목록 반환
            return new BaseResponse<>( shopService.getAllShops());
        }
    }
    @GetMapping("/detail")
    public BaseResponse<List<Shop>> getShopDetail(@RequestParam long shopId) {
        return new BaseResponse<>(shopService.getShopById(shopId));
    }

    @GetMapping("/food-categories")
    public List<FoodCategory> getAllFoodCategories() {
        return shopService.getAllFoodCategories();
    }
}
