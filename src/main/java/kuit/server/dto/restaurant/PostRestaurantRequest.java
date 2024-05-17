package kuit.server.dto.restaurant;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRestaurantRequest {
    private String name;
    private String location;
    private String phone;
    private String category;
    private int minOrderAmount;
    private String status;
}
