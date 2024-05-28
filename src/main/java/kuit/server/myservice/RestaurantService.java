package kuit.server.myservice;

import kuit.server.common.exception.UserException;
import kuit.server.mydao.RestaurantDao;
import kuit.server.mydto.retaurant.GetCategorizedRestaurantResp;
import kuit.server.mydto.retaurant.GetCategoryResp;
import kuit.server.mydto.retaurant.RestaurantReq;
import kuit.server.mydto.retaurant.RestaurantResp;
import kuit.server.mydto.retaurant.menu.PostMenuReq;
import kuit.server.mydto.retaurant.menu.PostMenuResp;
import kuit.server.mydto.retaurant.menu.RestaurantMenuResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.RESTAURANT_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;
    public RestaurantResp enroll(RestaurantReq restaurantReq) {
        log.info("RestaurantService.enroll");
        long restaurant_PK = restaurantDao.enrollRestaurant(restaurantReq);
        String jwt = "121212";
        return new RestaurantResp(restaurant_PK, jwt);
    }

    //음식점 메뉴 가져오기
    public List<RestaurantMenuResp> getRestaurantFoods(long restaurant_PK) {
        return restaurantDao.getRestaurantFoods(restaurant_PK);
    }

    //카테고리 목록 가져오기
    public List<GetCategoryResp> getCategories() {
        return restaurantDao.listUpCategories();
    }

    //카테고리별 식당 조회하기
    public List<GetCategorizedRestaurantResp> getCategorizedRestaurants(String category, long min_price, int page) {
        return restaurantDao.getCategorizedRestaurants(category, min_price, page);
    }

    //메뉴 등록 하기
    public PostMenuResp addMenu(long restaurant_PK, PostMenuReq postMenuReq) {
        log.info("RestaurantService.addMenu");
        validateRestaurant(restaurant_PK);
         Long food_PK = restaurantDao.addNewFood(restaurant_PK, postMenuReq);
         return new PostMenuResp(food_PK);
    }

    private void validateRestaurant(long restaurant_PK) {
        if(!restaurantDao.isExist(restaurant_PK)) {
            throw new UserException(RESTAURANT_NOT_FOUND);
        }
    }
}
