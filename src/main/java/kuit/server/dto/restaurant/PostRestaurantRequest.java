package kuit.server.dto.restaurant;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class PostRestaurantRequest {
    @NotBlank(message = "name : 이름은 공백일 수 없습니다.")
    @Length(max=30, message = "name : 이름은 최대 30자 이어야 합니다.")
    private String name;

    @NotBlank(message = "address : 주소는 공백일 수 없습니다.")
    @Length(max=150, message = "name : 이름은 최대 150자 이어야 합니다.")
    private String address;

    @NotBlank(message = "phone_number : 전화번호는 공백일 수 없습니다.")
    private String phone_number;

    @Min(value = 1, message = "category : 카테고리 번호는 1이상이어야 합니다.")
    private int category;
}
