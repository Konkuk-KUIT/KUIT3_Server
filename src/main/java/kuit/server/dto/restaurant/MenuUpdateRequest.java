package kuit.server.dto.restaurant;

import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class MenuUpdateRequest {
    @Nullable
    private String menu_name;

    @Nullable
    private int price;


}
