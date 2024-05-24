package kuit.server.dto.menu;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class PostMenuRequest {

    @NotBlank
    @Length(max = 1)
    private String menu_name;

    private long price;

    @NotBlank
    private String menu_image;

    @Nullable
    @Length(max = 100)
    private String menu_detail;

    private String menu_status;

//    private String created_date = "This is not requeried";
//    private String modified_date = "This is not requeried";
//
//    private long restaurantId;
}
