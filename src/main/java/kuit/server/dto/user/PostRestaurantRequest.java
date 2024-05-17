package kuit.server.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRestaurantRequest {

    private String address;
    private String category;
    private double latitude;
    private double longitude;
    private double min_price;
    private String name;
    private double star;
    private String picture_url;

}
