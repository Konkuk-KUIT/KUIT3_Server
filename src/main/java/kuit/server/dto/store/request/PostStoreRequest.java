package kuit.server.dto.store.request;

import kuit.server.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostStoreRequest {
    private Long storeId;
    private String name;
    private Long minimumPrice;
    private String status;

    public Store toEntity(){
        return new Store(this.storeId,this.name,this.minimumPrice,this.status);
    }
}
