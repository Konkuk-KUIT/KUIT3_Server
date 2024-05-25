package kuit.server.mydto.retaurant.menu;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostMenuReq {

    @NotBlank
    private String name;

    @NotNull(message = "메뉴에 가격을 적어주세요.")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private Long price;

    @Nullable
    private String explanation;
    @Nullable
    private String pic_URL;
}
