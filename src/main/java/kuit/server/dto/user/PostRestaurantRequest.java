package kuit.server.dto.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRestaurantRequest {

    @NotBlank(message = "address: {NotBlank}")
    private String address;

    @NotBlank(message = "category: {NotBlank}")
    private String category;

    @Max(value = 90, message = "latitude: 가질 수 있는 최대 값은 90입니다.")
    @Min(value = 0, message = "latitude: 가질 수 있는 최소 값은 0입니다.")
    private double latitude;

    @Max(value = 180, message = "longitude: 가질 수 있는 최대 값은 180입니다.")
    @Min(value = -180, message = "longitude: 가질 수 있는 최소 값은 -180입니다.")
    private double longitude;

    @PositiveOrZero(message = "min_price: 최소 금액은 0 이상의 정수입니다.")
    private double min_price;

    @NotBlank(message = "name: {NotBlank}")
    private String name;

    @PositiveOrZero(message = "star: 별점은 0~5의 값입니다.")
    @Max(value = 5, message = "star: 별점은 0~5의 값입니다.")
    private double star;

    @Nullable
    private String picture_url;

}
