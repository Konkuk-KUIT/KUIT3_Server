package kuit.server.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GetRestaurantResponse {
    String name;
    String address;
    String phone_number;
    String business_hour;
    String closed_day;
    int category;
    int minimum_order_price;
    String status;
    int star_rate;
    int delivery_fee;
}
