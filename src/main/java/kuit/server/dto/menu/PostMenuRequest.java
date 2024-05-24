package kuit.server.dto.menu;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class PostMenuRequest {

    @NotBlank
    @Length(max = 1, message = "메뉴이름은 적어도 하나의 문자 이상을 입력해야 합니다.")
    private String menu_name;

    private long price;

    @NotBlank
    private String menu_image;

    @Nullable
    @Length(max = 100)
    private String menu_detail;

    private String menu_status;

}
