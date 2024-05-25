package kuit.server.mydto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchEmailReq {

    @Email(message = "맞는 이메일 형식이 아닙니다.")
    @NotBlank(message = "email = {NotBlank}")
    @Length(max = 50, message = "최대 {max}자리까지 가능합니다.")
    String email;
}
