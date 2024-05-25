package kuit.server.dto.store.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class GetCategoryResponse {

    private String status;
    private String categoryName;
}
