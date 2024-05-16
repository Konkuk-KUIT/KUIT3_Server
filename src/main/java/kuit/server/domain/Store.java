package kuit.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Store {

    private Long storeId;
    private String name;
    private Long minimumPrice;
    private String status;
}
