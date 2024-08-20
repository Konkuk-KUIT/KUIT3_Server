package kuit.server.mydto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@Setter()
@NoArgsConstructor
public class PostMemberReq {

    @Email(message = "맞는 이메일 형식이 아닙니다.")
    @NotBlank(message = "email = {NotBlank}")
    @Length(max = 50, message = "최대 {max}자리까지 가능합니다.")
    private String email;
    @NotBlank
    @Length(min = 8, max = 20, message = "비밀번호는 최소 {min}자리, 최대 {max}자리여야 합니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}",
            message = "대소문자, 특수기호가 적어도 하나씩 포함되어야 합니다.")
    private String password;
    @NotBlank
    @Size(min = 11, max = 11, message = "전화번호는 '-'없이 11자리여야 합니다.")
    @Pattern(regexp = "/^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$/", message = "맞는 전화번호 형식을 작성해주세요.")
    private String phoneNumber;
    @NotBlank
    private String nickName;
    @Nullable
    private String profileImage;
}
