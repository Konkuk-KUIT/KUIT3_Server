package kuit.server.dto.store;

import kuit.server.domain.Store;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStoreResponse {
  private Long storeId;
  private String businessNumber;
  private String name;
  private Integer openTime;
  private Integer closeTime;
  private String noticeContent;
  private String description;
  private String originLabel;
  private String streetAddress;

  public static GetStoreResponse from(Store store) {
    return GetStoreResponse.builder()
      .storeId(store.getStoreId())
      .businessNumber(store.getBusinessNumber())
      .name(store.getName())
      .openTime(store.getOpenTime())
      .closeTime(store.getCloseTime())
      .noticeContent(store.getNoticeContent())
      .description(store.getDescription())
      .originLabel(store.getOriginLabel())
      .streetAddress(store.getStreetAddress())
      .build();
  }
}