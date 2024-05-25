package kuit.server.dto.store;

import lombok.Data;

@Data
public class GetStoreResponse {
    private long storeId;
    private String name;
    private int type;
    private String category;
    private String address;
    private String storePictureUrl;
    private String phone;
    private String content;
    private int minDeliveryPrice;
    private int deliveryTip;
    private int minDeliveryTime;
    private int maxDeliveryTime;
    private double rating;
    private int dibsCount;
    private int reviewCount;
    private String operationHours;
    private String closedDays;
    private String deliveryAddress;
    private String createdDate;
    private String modifiedDate;
    private String status;
}
