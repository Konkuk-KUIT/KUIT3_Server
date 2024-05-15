package kuit.server.dto.member;

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
}
