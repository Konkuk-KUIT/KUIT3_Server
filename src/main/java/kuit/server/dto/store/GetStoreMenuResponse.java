package kuit.server.dto.store;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class GetStoreMenuResponse {
    private final String name;
    private final String price;
}
