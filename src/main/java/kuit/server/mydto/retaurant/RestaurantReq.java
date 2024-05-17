package kuit.server.mydto.retaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReq {
    private String name;
    private long min_price;
    private String category;
    private String pic_URL;
}
