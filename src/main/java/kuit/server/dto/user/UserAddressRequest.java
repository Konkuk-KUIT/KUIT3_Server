package kuit.server.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserAddressRequest {
    @NotNull
    private String address_name;

    @NotNull
    private String request;

    private String area_password;

    private String instruction;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

}
