package kuit.server.service;

import kuit.server.dao.MenuDao;
import kuit.server.dao.RestaurantDao;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.restaurant.GetRestaurantResponse;
import kuit.server.dto.restaurant.PostRestaurantMenuRequest;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.dto.restaurant.PostRestaurantResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantDao restaurantDao;
    private final MenuDao menuDao;

    public PostRestaurantResponse createRestaurant(PostRestaurantRequest postRestaurantRequest){
        log.info("[RestaurantService.createRestaurant]");
        long restaurantId = restaurantDao.create(postRestaurantRequest);
        return new PostRestaurantResponse(restaurantId);
    }

    public List<GetRestaurantResponse> findRestaurantsByCategory(int categoryId) {
        return restaurantDao.findRestaurantsByCategory(categoryId);
    }

    public Long addMenu(long restaurantId, PostRestaurantMenuRequest postRestaurantMenuRequest) {
        if(restaurantDao.doesExistById(restaurantId)){
            PostMenuRequest menuRequest = new PostMenuRequest(
                    postRestaurantMenuRequest.getName(),
                    postRestaurantMenuRequest.getImage_url(),
                    postRestaurantMenuRequest.getDescription(),
                    postRestaurantMenuRequest.getPrice(),
                    restaurantId
            );
            return menuDao.createMenu(menuRequest);
        }
        return null;
    }

    public List<GetMenuResponse> getMenus(long restaurantId) {
//        if(restaurantDao.doesExistById(restaurantId)){
            return menuDao.getMenus(restaurantId);
//        }
//        return null;
    }

    public void modifyStatusAsClosed(Long restaurantId) {
        restaurantDao.modifyStatusAsClosed(restaurantId);
    }

    public void modifyBusinnessHour(Long restaurantId, String businessHour) {
        restaurantDao.modifyBusinessHour(restaurantId, businessHour);
    }

    public List<GetRestaurantResponse> search(String keyword, Integer minStar, String maxDeliveryFee) {
        if(minStar != null){
            return restaurantDao.search(keyword, minStar.toString(), maxDeliveryFee);
        }
        return restaurantDao.search(keyword, null, maxDeliveryFee);
    }
}
