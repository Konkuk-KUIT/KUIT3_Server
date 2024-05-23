package kuit.server.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class PatchNicknameRequest {

    @NotBlank(message = "nickname: {NotNull}")  
    @Length(max = 25, message = "nickname: 최대 {max}자리까지 가능합니다")
    private String nickname;

}