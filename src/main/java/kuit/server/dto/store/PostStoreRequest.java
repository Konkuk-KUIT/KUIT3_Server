package kuit.server.dto.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class PostStoreRequest {
    @NotBlank(message = "Name is required")
    @Length(max = 255, message = "Name: 최대 {max}자리까지 가능합니다")
    private String name;

    @NotBlank(message = "Address is required")
    @Length(max = 50, message = "Address: 최대 {max}자리까지 가능합니다")
    private String address;

    @NotBlank(message = "Food category is required")
    @Length(max = 50, message = "Food Category: 최대 {max}자리까지 가능합니다")
    private String foodCategory;

    @NotNull(message = "Store type is required")
    private Integer type;

    @NotBlank(message = "Phone number is required")
    @Length(min = 10, max = 11, message = "Phone Number: 최소 {min}자리 ~ 최대 {max}자리까지 가능합니다")
    private String phoneNumber;
}
