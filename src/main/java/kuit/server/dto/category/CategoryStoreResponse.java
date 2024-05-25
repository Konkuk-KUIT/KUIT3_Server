package kuit.server.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryStoreResponse {
    private String storeName;
    private int minOrderFee;
    private String addressName;
    private String workingTime;
    private String holiday;
}