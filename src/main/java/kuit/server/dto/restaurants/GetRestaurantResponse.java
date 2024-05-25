package kuit.server.dto.restaurants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantResponse {

    private String name;
    private String category;
    private String min_price;
    private String phoneNumber;
    private String storeImage;
    private String status;
}
