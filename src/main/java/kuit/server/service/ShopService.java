package kuit.server.service;

import kuit.server.common.exception.ShopException;
import kuit.server.dao.ShopDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import kuit.server.dao.ShopDao;
import kuit.server.dto.shop.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DUPLICATE_SHOP_NAME;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.FOODCATEGORY_NOT_MATCH;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopDao shopDAO;


    public List<Shop> getAllShops() {
        return shopDAO.getAllShops();
    }

    public List<Shop> getShopById(long shopId) {
        return shopDAO.getShopById(shopId);
    }

    public PostShopResponse addShop(PostShopRequest postShopRequest){
        log.info("[ShopController : add Shop]");
        validationShopName(postShopRequest.getShopName());

        return new PostShopResponse(shopDAO.createShop(postShopRequest));
    }
    public List<Shop> getShopsByAddress(String address) {
        return shopDAO.getShopsByAddress(address);
    }
    public List<Shop> getShopsByCategoryAndAddress(String category, String address) {
        if(!shopDAO.hasDuplicateFoodCategoryName(category)){
            throw new ShopException(FOODCATEGORY_NOT_MATCH);
        }
        return shopDAO.getShopsByCategoryAndAddress(category, address);
    }
    public List<Shop> getShopsByCategory(String category) {
        if(!shopDAO.hasDuplicateFoodCategoryName(category)){
            throw new ShopException(FOODCATEGORY_NOT_MATCH);
        }
        return shopDAO.getShopsByCategory(category);
    }
    public List<FoodCategory> getAllFoodCategories() {
        return shopDAO.getAllFoodCategories();
    }
    public String addFoodCategory(String foodCategory){

        if(shopDAO.hasDuplicateFoodCategoryName(foodCategory)){
            return "이미 존재하는 카테고리 입니다.";
        }
        shopDAO.createFoodCategory(foodCategory);
        return "새로운 카테고리 생성에 성공하였습니다";
    }

    private void validationShopName(String shopName){
        if(shopDAO.hasDuplicateShopName(shopName)){
            throw new ShopException(DUPLICATE_SHOP_NAME);
        }
    }
    private void validationFoodCategory(String shopName){
        if(shopDAO.hasDuplicateShopName(shopName)){
            throw new ShopException(DUPLICATE_SHOP_NAME);
        }
    }
}
