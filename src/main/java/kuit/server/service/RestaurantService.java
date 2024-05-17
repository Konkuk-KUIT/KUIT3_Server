package kuit.server.service;

import kuit.server.dao.RestaurantDao;
import kuit.server.dto.restaurant.GetRestaurantResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.dto.restaurant.PostRestaurantResponse;
import kuit.server.dto.user.GetUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public PostRestaurantResponse enroll(PostRestaurantRequest postRestaurantRequest) {
        long restaurantId = restaurantDao.createRestaurant(postRestaurantRequest);

        return new PostRestaurantResponse(restaurantId);
    }

    public List<GetRestaurantResponse> getRestaurants(String name, String phone) {
        log.info("getRestaurants");
        return restaurantDao.getRestaurants(name, phone);
    }

}
