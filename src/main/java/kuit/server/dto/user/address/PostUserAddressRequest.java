package kuit.server.dto.user.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@RequiredArgsConstructor
public class PostUserAddressRequest {
    @NotNull
    private long userId;

    @NotBlank
    @Length(max = 10)
    private String userAddress;

    @NotBlank
    @Length(max=20)
    private String addressCategory;
}
