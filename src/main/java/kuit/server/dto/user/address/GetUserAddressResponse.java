package kuit.server.dto.user.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GetUserAddressResponse {
    private String addressCategory;
    private String userAddress;

}
