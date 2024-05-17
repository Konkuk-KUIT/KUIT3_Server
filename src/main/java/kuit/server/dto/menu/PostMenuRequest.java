package kuit.server.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostMenuRequest {
    String name;
    String image_url;
    String description;
    int price;
    long restaurant_id;
}
