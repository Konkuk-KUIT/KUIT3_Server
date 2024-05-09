package kuit.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

    private String email;
    private String phoneNumber;
    private String nickname;
    private String profileImage;
    private String status;

}