package kuit.server.dto.menu;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class PostMenuRequest {

    private Long storeId;
    private String category;

    @NotBlank(message = "email: {NotBlank}")
    @Length(max = 10, message = "menu: 최대 {max}자리까지 가능합니다")
    private String name;
    private int price;
    private String menuPictureUrl;
}
