package kuit.server.service;

import kuit.server.dao.RestaurantDao;
import kuit.server.dto.restaurant.GetRestaurantResponse;
import kuit.server.dto.restaurant.PostRestaurantRequest;
import kuit.server.dto.restaurant.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public long registerRestaurant(PostRestaurantRequest postRestaurantRequest) {
        return restaurantDao.createRestaurant(postRestaurantRequest);
    }

    public void updateRestaurantStatus(long restaurantid, String status) {
        restaurantDao.modifyRestaurantStatus(restaurantid, status);
    }

    public void updateRestaurantDetails(long restaurantid, PostRestaurantRequest postRestaurantRequest) {
        restaurantDao.modifyRestaurantDetails(restaurantid, postRestaurantRequest.getName(), postRestaurantRequest.getLocation(),
                postRestaurantRequest.getPhone(), postRestaurantRequest.getCategory(), postRestaurantRequest.getMinOrderAmount());
    }

    public List<GetRestaurantResponse> getRestaurants(String name, String location, String status) {
        List<RestaurantDto> restaurantDtos = restaurantDao.getRestaurants(name, location, status);
        return restaurantDtos.stream()
                .map(dto -> new GetRestaurantResponse(
                        dto.getRestaurantid(),
                        dto.getName(),
                        dto.getLocation(),
                        dto.getPhone(),
                        dto.getCategory(),
                        dto.getMinOrderAmount(),
                        dto.getStatus()))
                .collect(Collectors.toList());
    }

    public GetRestaurantResponse getRestaurantById(long restaurantid) {
        RestaurantDto dto = restaurantDao.getRestaurantById(restaurantid);
        return new GetRestaurantResponse(
                dto.getRestaurantid(),
                dto.getName(),
                dto.getLocation(),
                dto.getPhone(),
                dto.getCategory(),
                dto.getMinOrderAmount(),
                dto.getStatus());
    }
}
