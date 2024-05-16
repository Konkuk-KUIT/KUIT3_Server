package kuit.server.dto.store.response;

import kuit.server.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetStoreResponse {

    private Long storeId;
    private String name;
    private String minimumPrice;
    private String status;

    public static GetStoreResponse of(Store store){
        return new GetStoreResponse(store.getStoreId(),store.getName(),store.getMinimumPrice(),store.getStatus());
    }
}
