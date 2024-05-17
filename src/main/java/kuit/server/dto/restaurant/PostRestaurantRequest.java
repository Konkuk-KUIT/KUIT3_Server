package kuit.server.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class PostRestaurantRequest {
    String name;
    String address;
    String phone_number;
    int category;
}
