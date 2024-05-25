package kuit.server.dto.menu;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
public class PostMenuRequest {
    @NotBlank(message = "name : 이름은 공백일 수 없습니다")
    @Length(max = 50, message = "name: 최대 {max}자리까지 가능합니다")
    private String name;

    @Nullable
    private String image_url;

    @Length(max = 150, message = "description: 최대 {max}자리까지 가능합니다")
    private String description;

    @Min(value = 0, message = "price : 메뉴의 가격은 0원 이상이어야 합니다")
    private int price;

    long restaurant_id;
}
