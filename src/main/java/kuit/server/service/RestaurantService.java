package kuit.server.service;


import kuit.server.dao.RestaurantDao;
import kuit.server.dao.UserDao;
import kuit.server.dto.restaurants.GetRestaurantResponse;
import kuit.server.dto.restaurants.PostRestaurantRequest;
import kuit.server.dto.restaurants.PostRestaurantResponse;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.dto.user.PostUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public static PostRestaurantResponse newRestaurant(PostRestaurantRequest postRestaurantRequest) {
        log.info("[RestaurantService.newRestaurant]");


        // TODO: 3. DB insert & userId 반환
        long userId = RestaurantDao.createRestaurant(postRestaurantRequest);


        return new PostRestaurantResponse(userId);
    }


    public List<GetRestaurantResponse> getRestaurants(String name, String email, String status) {
        log.info("[RestaurantService.getRestaurants]");
        return restaurantDao.getRestaurants(name, email, status);
    }
}
