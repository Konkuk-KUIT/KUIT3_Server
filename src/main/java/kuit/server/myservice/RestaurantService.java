package kuit.server.myservice;

import kuit.server.mydao.RestaurantDao;
import kuit.server.mydto.retaurant.GetCategoryResponse;
import kuit.server.mydto.retaurant.RestaurantReq;
import kuit.server.mydto.retaurant.RestaurantResp;
import kuit.server.mydto.retaurant.menu.RestaurantMenuResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<RestaurantMenuResp> getRestaurantFoods(long restaurant_PK) {
        return restaurantDao.getRestaurantFoods(restaurant_PK);
    }

    public List<GetCategoryResponse> getCategories() {
        return restaurantDao.listUpCategories();
    }
}
