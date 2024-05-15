package kuit.server.service;

import kuit.server.dao.RestaurantDao;
import kuit.server.dto.restaurant.MenuUpdateRequest;
import kuit.server.dto.restaurant.RestaurantMenuRequest;
import kuit.server.dto.restaurant.RestaurantMenuResponse;
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

    public List<RestaurantMenuResponse> getMenu(Long restaurantId) {
        return restaurantDao.getMenu(restaurantId);
    }

    public void createMenu(Long restaurantId, RestaurantMenuRequest menuRequest) {
        restaurantDao.createMenu(restaurantId,menuRequest);
    }

    public void modifyStatus_delete(Long restaurantId, Long menuId) {
        restaurantDao.modifyStatus_delete(restaurantId,menuId);
    }

    public void updateMenu(Long restaurantId, Long menuId, MenuUpdateRequest menuUpdateRequest) {
        restaurantDao.modifyMenu(restaurantId,menuId,menuUpdateRequest);
    }
}
