package kuit.server.dto.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Shop {
    private long shopId;
    private String shopName;
    private String shopCallNum;
    private boolean isOpenNow;
    private String address;
    private String foodCategory;

    // 생성자, getter 및 setter 메서드 생략


}
