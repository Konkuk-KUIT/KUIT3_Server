package kuit.server.mydto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostMemberReq {

    private String email;
    private String password;
    private String phoneNumber;
    private String nickname;
    private String profileImage;
}
