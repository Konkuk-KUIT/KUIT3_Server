package kuit.server.mydto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchPasswordReq {

    @NotBlank
    @Length(min = 8, max = 20, message = "비밀번호는 최소 {min}자리, 최대 {max}자리여야 합니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}",
            message = "대소문자, 특수기호가 적어도 하나씩 포함되어야 합니다.")
    String password;
}
