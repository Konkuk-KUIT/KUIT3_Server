package kuit.server.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class PostUserRequest {
    // 예외 처리 메세지
    @Email(message = "email: 이메일 형식이어야 합니다")
    // null, 빈 문자열, 공백 제외
    @NotBlank(message = "email: {NotBlank}")
    // 길이
    @Length(max = 50, message = "email: 최대 {max}자리까지 가능합니다")
    private String email;

    @NotBlank(message = "password: {NotBlank}")
    @Length(min = 8, max = 20,
            message = "password: 최소 {min}자리 ~ 최대 {max}자리까지 가능합니다")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}",
            message = "password: 대문자, 소문자, 특수문자가 적어도 하나씩은 있어야 합니다")
    private String password;

    @NotBlank(message = "phoneNumber: {NotBlank}")
    @Length(max = 20, message = "phoneNumber: 최대 {max}자리까지 가능합니다")
    private String phoneNumber;

    @Nullable
    @Length(max = 25, message = "nickname: 최대 {max}자리까지 가능합니다")
    private String nickname;

    @Nullable
    private String profileImage;

    public void resetPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

}