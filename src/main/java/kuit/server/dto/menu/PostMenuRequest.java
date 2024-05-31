package kuit.server.dto.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class PostMenuRequest {
  @NotBlank
  private String name;
  @Nullable
  private String description;
  @NotNull
  private Integer price;
}