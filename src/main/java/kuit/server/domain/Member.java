package kuit.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Member {

    private Long memberId;
    private String name;
    private String nickname;
    private String password;
    private String phoneNum;
    private String email;

}
