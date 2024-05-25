package kuit.server.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuResponse {
    private String name;
    private String category;
    private int price;
    private String menuPictureUrl;
    private String status;
}
