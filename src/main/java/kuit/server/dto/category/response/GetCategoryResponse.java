package kuit.server.dto.category.response;

import kuit.server.domain.Category;
import kuit.server.domain.Member;
import kuit.server.dto.member.response.GetMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCategoryResponse {

    private Long category_id;
    private Long store_id;
    private String name;

    public static GetCategoryResponse of(Category category){
        return new GetCategoryResponse(category.getCategory_id(),category.getStore_id(),category.getName());
    }
}
