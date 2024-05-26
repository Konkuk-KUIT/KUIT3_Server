package kuit.server.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostRestaurantMenuRequest {
    String name;
    String image_url;
    String description;
    int price;
}
