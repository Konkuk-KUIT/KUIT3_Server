package kuit.server.dto.user;

import kuit.server.domain.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

    private String email;
    private String phone;
    private String nickname;
    private String profileImage;
    private String status;

  public static GetUserResponse from(User user) {
    return GetUserResponse.builder()
      .email(user.getEmail())
      .phone(user.getPhone())
      .nickname(user.getNickname())
      .profileImage(user.getProfileImage())
      .status(user.getStatus())
      .build();
  }
}