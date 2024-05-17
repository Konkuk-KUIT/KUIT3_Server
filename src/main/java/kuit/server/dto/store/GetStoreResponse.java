package kuit.server.dto.store;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
}