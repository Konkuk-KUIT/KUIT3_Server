package kuit.server.mydto.retaurant;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReq {
    @NotBlank
    @Length(max = 50, message = "이름은 최대{max}까지만 가능합니다.")
    private String name;
    @Nullable
    private long min_price;
    @NotBlank
    private String category;
    @Nullable
    private String pic_URL;
}
