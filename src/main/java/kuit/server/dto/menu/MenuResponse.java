package kuit.server.dto.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class MenuResponse {
    private Long menuId;
    private String menuName;
    private int price;
    private String optionName;
}
