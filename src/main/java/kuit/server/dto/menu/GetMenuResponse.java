package kuit.server.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMenuResponse {
    private String name;
    private String image_url;
    private String description;
    private int price;
    private long restaurant_id;
}
