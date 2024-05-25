package kuit.server.dto.order;

import lombok.Data;

@Data
public class PostOrderRequest {
    private long userId;
    private long storeId;
    private OrderDetail[] orderDetail;
}
