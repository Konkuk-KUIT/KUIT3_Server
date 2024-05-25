package kuit.server.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrdersResponse {
    private String StoreName;
    private String menuName;
    private int price;
    private int quantity;
    private String updated_at;
}
