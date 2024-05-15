package kuit.server.dto.member.request;

import kuit.server.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostMemberRequest {

    private Long memberId;
    private String name;
    private String nickname;
    private String password;
    private String phone_num;
    private String email;

    public Member toEntity(){
        return new Member(this.memberId,this.name,this.nickname,this.password,this.phone_num,this.email);
    }
}
