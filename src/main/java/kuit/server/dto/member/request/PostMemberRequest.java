package kuit.server.dto.member.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import kuit.server.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostMemberRequest {

    @Nonnull
    private Long memberId;

    @NotBlank(message = "name: 공백은 허용되지 않습니다")
    private String name;

    @NotBlank(message = "nickname: 공백은 허용되지 않습니다")
    private String nickname;

    @NotBlank(message = "password: 공백은 허용되지 않습니다")
    private String password;

    @NotBlank(message = "phoneNum: 공백은 허용되지 않습니다")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "phoneNum: 유효한 휴대폰 번호 형식이어야 합니다. 예: 010-1234-5678")
    private String phoneNum;

    @Email(message = "email: 이메일 형식이어야 합니다")
    private String email;

    public Member toEntity(){
        return new Member(this.memberId,this.name,this.nickname,this.password,this.phoneNum,this.email);
    }

}
