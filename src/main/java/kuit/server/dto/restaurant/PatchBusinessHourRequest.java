package kuit.server.dto.restaurant;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor  // 왜 이게 있어야 정상동작할까...
public class PatchBusinessHourRequest {

    @Length(max=100, message="businenss_hour : 입력할 수 있는 길이는 {max}입니다.")
    private String business_hour;
}
