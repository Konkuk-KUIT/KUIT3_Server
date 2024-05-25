package kuit.server.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetOrderResponse {
    private long orderId;
    private String status;
    private float total;
}
