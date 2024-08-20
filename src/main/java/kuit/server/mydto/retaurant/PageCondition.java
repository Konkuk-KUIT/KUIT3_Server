package kuit.server.mydto.retaurant;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageCondition {

    @NotBlank(message = "페이지 번호가 필요합니다.")
    @Min(value = 0, message = "페이지 번호는 최소 {value} 이상이어야 합니다.")
    private int lastId;
    @Nullable
    private String sortDirectionBy;
    @Nullable
    private long numSortBy;
}
