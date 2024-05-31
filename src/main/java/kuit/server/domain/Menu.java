package kuit.server.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.type.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity(name = "menu")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long menuId;
  private Long storeId;
  @Setter
  private String name;
  @Setter
  private String description;
  @Setter
  private Integer price;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static Menu from(PostMenuRequest request, long storeId) {
    return Menu.builder()
      .name(request.getName())
      .storeId(storeId)
      .description(request.getDescription())
      .price(request.getPrice())
      .status(Status.ACTIVE.toString().toLowerCase())
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .build();
  }
}
