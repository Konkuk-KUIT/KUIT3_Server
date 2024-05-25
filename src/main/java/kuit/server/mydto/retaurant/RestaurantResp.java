package kuit.server.mydto.retaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantResp {
    private long restaurant_PK;
    private String jwt;
}
