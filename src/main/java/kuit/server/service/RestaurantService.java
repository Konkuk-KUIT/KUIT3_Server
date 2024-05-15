package kuit.server.service;

import kuit.server.dao.RestaurantDao;
import kuit.server.dto.restaurant.RestaurantOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public List<RestaurantOrderResponse> getOrders(Long restaurantId) {
        return restaurantDao.getOrders(restaurantId);
    }
}
