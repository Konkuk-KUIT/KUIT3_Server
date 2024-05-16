package kuit.server.dto.store.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinStoreCategory {

    private Long storeId;
    private String storeName;
    private Long minimumPrice;
    private String status;
    private String categoryName;
}
