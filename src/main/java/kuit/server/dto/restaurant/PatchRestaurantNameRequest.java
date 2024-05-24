package kuit.server.dto.restaurant;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class PatchRestaurantNameRequest
{
    @NotBlank(message = "restaurantname: {NotBlank}")
    @Length(max = 20, message = "restaurantname: 최대 {max}자리까지 가능합니다")
    private String restaurantname;

}
