package kuit.server.dto.restaurant;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class RestaurantMenuRequest {

    @Nullable
    @Length(max = 25, message = "menuName: 최대 {max}자리까지 가능합니다")
    private String menuName;

    @Nullable
    private int price;
}
