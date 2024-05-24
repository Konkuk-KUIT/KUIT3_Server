package kuit.server.dto.shop;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@RequiredArgsConstructor
public class PostShopRequest {
    @NotBlank(message = "shopName: {NotBlank}")
    @Length(min=2, max=20,message = "shopName: 최소 {min}자리 ~ 최대 {max}자리까지 가능합니다")
    private String shopName;

    @NotBlank(message = "shop PhoneNumber: {NotBlank}")
    @Length(max = 20, message = "phoneNumber: 최대 {max}자리까지 가능합니다")
    private String shopCallNum;

    @NotBlank(message = "address: {NotBlank}")
    @Length(max = 50, message = "address: 최대 {max}자리까지 가능합니다")
    private String address;

    @NotBlank(message = "foodCategory: {NotBlank}")
    @Length(max = 10, message = "foodCategory: 최대 {max}자리까지 가능합니다")
    private String foodCategory;
}
