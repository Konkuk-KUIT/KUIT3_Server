package kuit.server.dto.store.response;

import kuit.server.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostStoreResponse {
    private Long storeId;
    private String name;
    private Long minimumPrice;
    private String status;

    public static PostStoreResponse of(Store store){
        return new PostStoreResponse(store.getStoreId(),store.getName(),store.getMinimumPrice(),store.getStatus());
    }
}
