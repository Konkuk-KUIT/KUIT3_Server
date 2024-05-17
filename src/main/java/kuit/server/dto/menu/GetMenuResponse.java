package kuit.server.dto.menu;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetMenuResponse {
  private Long menuId;
  private String name;
  private String description;
  private Integer price;
}