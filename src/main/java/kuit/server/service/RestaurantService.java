package kuit.server.service;

import kuit.server.dao.RestaurantDao;
import kuit.server.dto.user.PostRestaurantRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public long makeNewRestaurant(PostRestaurantRequest postRestaurantRequest) {
        return restaurantDao.createRestaurant(postRestaurantRequest);
    }

}
