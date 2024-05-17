package kuit.server.mydto.retaurant.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenuReq {

    private String name;
    private long price;
    private String explanation;
    private String pic_URL;
}
