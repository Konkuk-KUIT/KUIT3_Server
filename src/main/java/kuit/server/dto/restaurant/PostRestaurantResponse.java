package kuit.server.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRestaurantResponse {
    private long restaurantid;
    private String jwt;
}
