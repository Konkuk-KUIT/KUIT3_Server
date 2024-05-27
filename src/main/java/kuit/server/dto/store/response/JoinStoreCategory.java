package kuit.server.dto.store.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class JoinStoreCategory {

    private Long storeId;
    private String storeName;
    private Long minimumPrice;

    private List<GetCategoryResponse>categoryResponseList=new ArrayList<>();

    public void pushCategory(GetCategoryResponse getCategoryResponse){
        this.categoryResponseList.add(getCategoryResponse);
    }
}
