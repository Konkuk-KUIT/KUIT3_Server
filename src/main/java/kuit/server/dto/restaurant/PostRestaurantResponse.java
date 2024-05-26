package kuit.server.dto.restaurant;

import lombok.Getter;

@Getter
public class PostRestaurantResponse {

    private long restaurantId;

    private String jwt;

    public PostRestaurantResponse(long restaurantId) {
        this.restaurantId = restaurantId;
    }

}
