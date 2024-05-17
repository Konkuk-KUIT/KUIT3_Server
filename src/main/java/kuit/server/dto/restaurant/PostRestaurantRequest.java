package kuit.server.dto.restaurant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRestaurantRequest {

    private int restaurantId;

    private String name;

    private String location;

    private String phone;

    private String category;

    private int minOrderAmount;

}
