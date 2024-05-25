package kuit.server.dto.restaurant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRestaurantRequest {
    @NotBlank(message = "restaurantname: {NotBlank}")
    @Length(max = 20, message = "restaurantname: 최대 {max}자리까지 가능합니다")
    private String restaurantname;
    @NotBlank(message = "category: {NotBlank}")
    @Length(max = 10, message = "category: 최대 {max}자리까지 가능합니다")
    private String category;
    @NotNull(message = "min_order_amount: {Notnull}")
    @Range(min = 0,max = 100000,message = "min_order_amount : 최소주문금액은 최대 {max}원 까지 가능합니다")
    private long min_order_amount;
}
