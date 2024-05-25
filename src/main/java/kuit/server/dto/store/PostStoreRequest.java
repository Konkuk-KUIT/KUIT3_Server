package kuit.server.dto.store;

import lombok.Data;

@Data
public class PostStoreRequest {
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
    private String operationHours;
    private String closedDays;
    private String deliveryAddress;
}
