package kuit.server.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserOrderHistoryResponse {
    private long userId;
    private long orderId;
    private String status;
    private float total;
}
