package kuit.server.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostOrderRequest {

    @NotBlank(message = "paymentMethod: {NotBlank}")
    private String paymentMethod;

    @NotNull(message = "totalPrice: {NotNull}")
    @Min(value = 0)
    private Integer totalPrice;

    @NotNull(message = "restaurantId: {NotNull}")
    private Long restaurantId;

}
