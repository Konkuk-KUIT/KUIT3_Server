package kuit.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Store {

    private Long storeId;
    private String name;
    private String minimumPrice;
    private String status;
}
