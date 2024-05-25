package kuit.server.dto.store;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@NoArgsConstructor
public class PatchFoodCategoryRequest {
    @NotNull(message = "Food category: {NotNull}")
    private String foodCategory;
}