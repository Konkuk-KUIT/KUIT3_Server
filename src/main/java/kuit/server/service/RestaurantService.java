package kuit.server.service;

import kuit.server.common.exception.RestaurantException;
import kuit.server.common.exception.UserException;
import kuit.server.dao.RestaurantDao;
import kuit.server.dto.restaurant.GetRestaurant;
import kuit.server.dto.restaurant.GetcategoryResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DUPLICATE_EMAIL;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.DUPLICATE_RESTAURANT_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;
    public List<GetcategoryResponse> getCatogories(String category) {
        log.info("[Restaurant.getCategory]");
        return restaurantDao.getCategory(category);
    }
    public List<GetRestaurant> getRestaurants() {
        log.info("[Restaurant.getRestaurants]");
        return restaurantDao.getRestaurants();
    }
    public long createRestaurant(PostRestaurantRequest postRestaurantRequest){
        validateRestaurantName(postRestaurantRequest.getRestaurantname());
        return restaurantDao.createRestaurant(postRestaurantRequest);
    }

    private void validateRestaurantName(String restaurantname) {
        if (restaurantDao.hasDuplicateRestaurantName(restaurantname)) {
            throw new RestaurantException(DUPLICATE_RESTAURANT_NAME);
        }
    }
}
