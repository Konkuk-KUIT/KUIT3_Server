package kuit.server.mydto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostLoginReq {

    @Email(message = "맞는 이메일 형식이 아닙니다.")
    @NotBlank(message = "email = {NotBlank}")
    private String email;
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}",
            message = "대소문자, 특수기호가 적어도 하나씩 포함되어야 합니다.")
    @NotBlank(message = "비밀번호를 작성해주세요.")
    private String password;
}
