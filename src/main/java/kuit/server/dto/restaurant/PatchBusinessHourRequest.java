package kuit.server.dto.restaurant;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor  // 왜 이게 있어야 정상동작할까...
public class PatchBusinessHourRequest {
    String business_hour;
}
