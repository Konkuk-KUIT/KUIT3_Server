package kuit.server.dto.restaurant;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRestaurantRequest {

    @NotBlank(message = "name: {NotBlank}")
    private String name;

    @NotBlank(message = "location: {NotBlank}")
    private String location;

    @Nullable
    private String phone;

    @NotBlank(message = "category: {NotBlank}")
    private String category;

    private int minOrderAmount;

}
