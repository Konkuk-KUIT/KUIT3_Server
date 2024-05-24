package kuit.server.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GetRestaurantResponse {
    private String name;
    private String address;
    private String phone_number;
    private String business_hour;
    private String closed_day;
    private int category;
    private int minimum_order_price;
    private String status;
    private int star_rate;
    private int delivery_fee;
}
