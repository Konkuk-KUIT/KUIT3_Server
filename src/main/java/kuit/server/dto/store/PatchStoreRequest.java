package kuit.server.dto.store;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class PatchStoreRequest {

  @Max(value = 24, message = "openTime: 최대 {max}시 까지 가능합니다")
  private Integer openTime;

  @Max(value = 24, message = "openTime: 최대 {max}시 까지 가능합니다")
  private Integer closeTime;

  @Min(value = 0, message = "closeTime: 최소 {min}원 까지 가능합니다")
  private Integer minOrderAmount;

  @NotNull
  private Long latitude;

  @NotNull
  private Long longitude;

  @NotBlank
  private String streetAddress;

  @Nullable
  @Length(max = 100, message = "noticeContent: 최대 {max}자까지 가능합니다")
  private String addressDetail;

  @Nullable
  @Length(max = 200, message = "noticeContent: 최대 {max}자까지 가능합니다")
  private String noticeContent;

  @Nullable
  @Length(max = 200, message = "description: 최대 {max}자까지 가능합니다")
  private String description;

  @Nullable
  @Length(max = 500, message = "description: 최대 {max}자까지 가능합니다")
  private String originLabel;
}