package kuit.server.dto.restaurant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantMenuResponse {
    private String menuName;
    private int price;
}
