package kuit.server.myservice;

import kuit.server.common.exception.UserException;
import kuit.server.mydto.retaurant.*;
import kuit.server.mydao.RestaurantDao;
import kuit.server.mydto.retaurant.menu.PostMenuReq;
import kuit.server.mydto.retaurant.menu.PostMenuResp;
import kuit.server.mydto.retaurant.menu.RestaurantMenuResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.Pageable;
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
    public GetCategorizedRestaurantResp getCategorizedRestaurants(String category, PageCondition pageCondition) {
        if(pageCondition.getSortDirectionBy().isBlank()) {
            pageCondition.setSortDirectionBy("desc");
        }
        //다음 데이터 조회를 위한 시작 위치 늘려 주기
        pageCondition.setLastId(pageCondition.getLastId()+1);
        GetCategorizedRestaurantResp response = new GetCategorizedRestaurantResp();
        List<CategorizedRestaurantEntity> restaurants = restaurantDao.getCategorizedRestaurantRespList(category, pageCondition)
        response.setCategorizedRestaurants(restaurants);

        if(restaurants.size() > restaurantDao.DEFAULT_SIZE + 1) {
            //조회 가능 엔티티가 있음을 알려줌
            response.setHasNextEntity(true);
            //다음번 조회 때도 +1을 했을 때 정상 작동하도록 숫자 -1 (-1을 하지 않으면 조회를 할 때마다 +1이 계속 누적된다!!)
            response.setLastId(restaurants.size()-1);
        } else {
            response.setHasNextEntity(false);
        }
        return response;

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

    public List<GetCategorizedRestaurantResp> getCategorizedRestaurantsV2(String category, Pageable pageable) {
        log.info("RestaurantService.getCategorizedRestaurantsV2");
    }
}
