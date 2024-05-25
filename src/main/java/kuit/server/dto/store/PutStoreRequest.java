package kuit.server.dto.store;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PutStoreRequest {
    @NotBlank(message = "name: {NotBlank}")
    @Length(min = 1, max = 30, message = "name: 최소 {min}자리 ~ 최대 {max}자리까지 가능합니다")
    private String name;

    @Nullable
    private int type;

    @NotBlank(message = "category: {NotBlank}")
    private String category;

    @NotBlank(message = "address: {NotBlank}")
    @Length(min = 2, max = 20, message = "address: 최소 {min}자리 ~ 최대 {max}자리까지 가능합니다")
    private String address;

    @NotBlank(message = "storePictureUrl: {NotBlank}")
    private String storePictureUrl;

    @NotBlank(message = "phone: {NotBlank}")
    @Length(max = 20, message = "phoneNumber: 최대 {max}자리까지 가능합니다")
    private String phone;

    @NotBlank(message = "content: {NotBlank}")
    private String content;

    @NotBlank(message = "minDeliveryPrice: {NotBlank}")
    private int minDeliveryPrice;

    @NotBlank(message = "deliveryTip: {NotBlank}")
    private int deliveryTip;

    @NotBlank(message = "minDeliveryTime: {NotBlank}")
    private int minDeliveryTime;

    @NotBlank(message = "maxDeliveryTime: {NotBlank}")
    private int maxDeliveryTime;

    @Nullable
    private double rating;

    @Nullable
    private int dibsCount;

    @Nullable
    private int reviewCount;

    @NotBlank(message = "operationHours: {NotBlank}")
    private String operationHours;

    @NotBlank(message = "closedDays: {NotBlank}")
    private String closedDays;

    @NotBlank(message = "deliveryAddress: {NotBlank}")
    private String deliveryAddress;

    @Nullable
    private String status;
}
