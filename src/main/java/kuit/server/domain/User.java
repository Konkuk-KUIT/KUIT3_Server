package kuit.server.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.type.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  private String email;
  private String phone;
  @Setter
  private String nickname;
  private String profileImage;
  private String password;
  @Setter
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static User from(PostUserRequest postUserRequest) {
    return User.builder()
      .email(postUserRequest.getEmail())
      .phone(postUserRequest.getPhone())
      .nickname(postUserRequest.getNickname())
      .password(postUserRequest.getPassword())
      .profileImage(postUserRequest.getProfileImage())
      .status(Status.ACTIVE.toString())
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .build();
  }
}
