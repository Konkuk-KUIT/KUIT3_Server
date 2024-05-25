package kuit.server.dto.order;

import lombok.Data;

@Data
public class OrderDetail {
    private long menuId;
    private long count;
    private Custom[] custom;
}
