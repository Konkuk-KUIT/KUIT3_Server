package kuit.server.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreResponse {
    private long storeId;
    private String name;
    private String address;
    private String foodCategory;
    private int type;
    private String phoneNumber;
}
