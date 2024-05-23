package kuit.server.dto.user;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
public class UserAddressRequest {
    @NotBlank
    @NotBlank(message = "이름을 입력해주세요")
    @Length(max = 10, message = "address_name: 최대 {max}자리까지 가능합니다")
    private String address_name;

    @NotBlank(message = "요청 사항을 입력해주세요")
    @Length(max = 25, message = "request: 최대 {max}자리까지 가능합니다")
    private String request;

    @Length(max = 10, message = "area_password: 최대 {max}자리까지 가능합니다")
    private String area_password;

    @Length(max = 25, message = "instruction: 최대 {max}자리까지 가능합니다")
    private String instruction;

    @NotNull(message = "위도를 입력해주세요")
    @DecimalMin(value = "-90.0", message = "위도는 -90 이상이어야 합니다")
    @DecimalMax(value = "90.0", message = "위도는 90 이하이어야 합니다")
    private BigDecimal latitude;

    @NotNull(message = "경도를 입력해주세요")
    @DecimalMin(value = "-180.0", message = "경도는 -180 이상이어야 합니다")
    @DecimalMax(value = "180.0", message = "경도는 180 이하이어야 합니다")
    private BigDecimal longitude;

}
