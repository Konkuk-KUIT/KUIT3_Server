package kuit.server.dto.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostMenuRequest {
    private Long storeId;
    private String category;
    private String name;
    private int price;
    private String menuPictureUrl;
}
