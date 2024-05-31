package kuit.server.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kuit.server.dto.store.PatchStoreRequest;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.type.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity(name = "store")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long storeId;
  private Long userId;
  private String businessNumber;
  private String name;
  private Integer openTime;
  private Integer closeTime;
  private String noticeContent;
  private String description;
  private String originLabel;
  private String streetAddress;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static Store from(PostStoreRequest request, long userId) {
    return Store.builder()
      .businessNumber(request.getBusinessNumber())
      .userId(userId)
      .name(request.getName())
      .openTime(request.getOpenTime())
      .closeTime(request.getCloseTime())
      .noticeContent(request.getNoticeContent())
      .description(request.getDescription())
      .originLabel(request.getOriginLabel())
      .streetAddress(request.getStreetAddress())
      .status(Status.ACTIVE.toString().toLowerCase())
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .build();
  }
  public static Store from(PatchStoreRequest request) {
    return Store.builder()
      .openTime(request.getOpenTime())
      .closeTime(request.getCloseTime())
      .noticeContent(request.getNoticeContent())
      .description(request.getDescription())
      .originLabel(request.getOriginLabel())
      .streetAddress(request.getStreetAddress())
      .status(Status.ACTIVE.toString().toLowerCase())
      .updatedAt(LocalDateTime.now())
      .build();
  }
}
