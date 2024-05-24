package kuit.server.dto.menu;

import kuit.server.domain.Menu;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetMenuResponse {
  private Long menuId;
  private String menuName;
  private String description;
  private Integer price;

  public static GetMenuResponse from(Menu menu) {
    return GetMenuResponse.builder()
      .menuId(menu.getMenuId())
      .menuName(menu.getName())
      .description(menu.getDescription())
      .description(menu.getDescription())
      .build();
  }
}