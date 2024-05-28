package kuit.server.mydto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class GetUserInfoResp {

    String password;
    String phoneNumber;
    String profileImage;

}
