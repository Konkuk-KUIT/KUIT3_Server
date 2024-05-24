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
    @NotBlank(message = "가게이름은 필수 입력사항입니다.")
    @Length(max = 255, message = "가게이름 : 최대 {max}자리까지 가능합니다")
    private String name;

    @NotBlank(message = "가게주소는 필수 입력사항입니다.")
    @Length(max = 50, message = "가게주소 : 최대 {max}자리까지 가능합니다")
    private String address;

    @NotBlank(message = "음식카테고리는 필수 입력사항입니다.")
    @Length(max = 50, message = "음식 카테고리 : 최대 {max}자리까지 가능합니다")
    private String foodCategory;

    @NotNull(message = "가게타입은 필수 입력사항입니다.")
    private Integer type;

    @NotBlank(message = "가게번호는 필수 입력사항입니다.")
    @Length(min = 10, max = 11, message = "전화번호 : 최소 {min}자리 ~ 최대 {max}자리까지 입력가능합니다")
    private String phoneNumber;
}
