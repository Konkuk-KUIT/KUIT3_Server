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
    @Length(max = 30, message = "Name: 최대 {max}자리까지 가능합니다")
    private String name;

    @NotBlank(message = "Address is required")
    @Length(max = 30, message = "Address: 최대 {max}자리까지 가능합니다")
    private String address;

    @NotBlank(message = "Food category is required")
    @Length(max = 30, message = "Food Category: 최대 {max}자리까지 가능합니다")
    private String foodCategory;

}