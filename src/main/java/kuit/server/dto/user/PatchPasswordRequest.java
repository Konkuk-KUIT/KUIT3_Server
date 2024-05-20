package kuit.server.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class PatchPasswordRequest {

    @NotNull(message = "nickname: {NotNull}")
    @Length(min = 8, max = 20, message = "password: 최소 8자리 ~ 최대 20자리까지 가능합니다")
    private String password;
}
