package kuit.server.dto.wishlist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GetWishlistResponse {
    private long shopId;
    private String shopName;

}
