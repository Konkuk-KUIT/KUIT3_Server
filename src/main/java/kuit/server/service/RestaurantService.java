package kuit.server.service;

import kuit.server.dao.RestaurantDao;
import kuit.server.dto.restaurant.GetCategoryResponse;
import kuit.server.dto.restaurant.GetStoreResponse;
import kuit.server.dto.user.GetUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public List<GetStoreResponse> getAllStores() {
        log.info("[RestaurantService.getAllStores]");
        return restaurantDao.getAllStores();
    }
    public List<GetStoreResponse> getStoresSortedByMinimumPrice(long minimumPrice) {
        log.info("[RestaurantService.getStoresSortedByMinimumPrice]");
        return restaurantDao.getStoresSortedByMinimumPrice(minimumPrice);
    }
    public List<GetCategoryResponse> getRestaurants() {
        log.info("[RestaurantService.getRestaurants]");
        return restaurantDao.getCategories();
    }
    public List<GetStoreResponse> getSpecificCategories(long categoryId) {
        log.info("[RestaurantService.getSpecificCategories]");
        return restaurantDao.getCategoryStores(categoryId);
    }
}
