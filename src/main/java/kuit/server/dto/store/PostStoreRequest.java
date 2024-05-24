package kuit.server.dto.store;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PostStoreRequest {

    @NotBlank(message = "name: {NotBlank}")
    @Length(max = 25, message = "name: 최대 {max}자리까지 가능합니다")
    private String name;

    @Nullable
    private int type;

    @NotBlank(message = "category: {NotBlank}")
    private String category;

    @NotBlank(message = "address: {NotBlank}")
    private String address;

    @Nullable
    private String storePictureUrl;

    @NotBlank(message = "phone: {NotBlank}")
    @Length(max = 20, message = "phoneNumber: 최대 {max}자리까지 가능합니다")
    private String phone;

    @NotBlank(message = "content: {NotBlank}")
    private String content;

    @NotBlank(message = "minDeliveryPrice: {NotBlank}")
    private int minDeliveryPrice;

    @Nullable
    private int deliveryTip;

    @NotBlank(message = "minDeliveryTime: {NotBlank}")
    private int minDeliveryTime;

    @NotBlank(message = "maxDeliveryTime: {NotBlank}")
    private int maxDeliveryTime;

    @NotBlank(message = "operationHours: {NotBlank}")
    private String operationHours;

    @NotBlank(message = "closedDays: {NotBlank}")
    private String closedDays;

    @NotBlank(message = "deliveryAddress: {NotBlank}")
    private String deliveryAddress;
}
