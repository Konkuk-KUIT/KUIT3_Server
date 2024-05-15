package kuit.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Member {

    private Long memberId;
    private String name;
    private String nickname;
    private String password;
    private String phone_num;
    private String email;

}
