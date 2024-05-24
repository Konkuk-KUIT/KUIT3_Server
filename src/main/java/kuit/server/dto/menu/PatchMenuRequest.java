package kuit.server.dto.menu;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchMenuRequest {
    @Min(value = 0, message = "price : 메뉴의 가격은 0원 이상이어야 합니다")
    private Integer price;
}
