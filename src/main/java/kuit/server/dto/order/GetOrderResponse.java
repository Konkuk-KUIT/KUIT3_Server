package kuit.server.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponse {
    private Long orderId;
    private String paymentMethod;
    private Integer totalPrice;
    private String status;
    private Long userId;
    private Long restaurantId;
}
