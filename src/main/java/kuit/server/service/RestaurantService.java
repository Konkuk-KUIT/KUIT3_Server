package kuit.server.service;

import kuit.server.dao.RestaurantDao;
import kuit.server.dto.restaurant.GetCategoryResponse;
import kuit.server.dto.restaurant.GetRestaurantResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public List<GetRestaurantResponse> getRestaurants(){
        return restaurantDao.getRestaurants();
    }
    public List<GetCategoryResponse> getCategories(){
        return restaurantDao.getCategories();
    }

    public List<GetRestaurantResponse> getRestaurantsByCategory(Long categoryId){
        return restaurantDao.getRestaurantsByCategory(categoryId);
    }


}
