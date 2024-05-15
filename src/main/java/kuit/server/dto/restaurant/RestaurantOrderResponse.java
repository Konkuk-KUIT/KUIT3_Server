package kuit.server.dto.restaurant;

import kuit.server.dto.menu.MenuResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class RestaurantOrderResponse {
    private Long orderId;
    private Long userId;
    private List<MenuResponse> menuList;
}
