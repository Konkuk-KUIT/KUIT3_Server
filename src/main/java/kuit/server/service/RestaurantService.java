package kuit.server.service;

import kuit.server.common.exception.RestaurantException;
import kuit.server.dao.RestaurantDao;
import kuit.server.dto.restaurant.GetRestaurant;
import kuit.server.dto.restaurant.GetcategoryResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DUPLICATE_RESTAURANT_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;
    public List<GetRestaurant> getRestaurants(String restaurantname, String category, String status) {
        log.info("[Restaurant.getRestaurants]");
        return restaurantDao.getRestaurants(restaurantname,category,status);
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
