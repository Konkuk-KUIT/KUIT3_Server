package kuit.server.controller;

import kuit.server.common.argument_resolver.PreAuthorize;
import kuit.server.common.exception.StoreException;
import kuit.server.common.response.BaseResponse;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PatchStoreRequest;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_STORE_VALUE;
import static kuit.server.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

  private final StoreService storeService;

  /**
   * 가게 신규 등록
   */
  @PostMapping("/register")
  public BaseResponse<Object> register(@PreAuthorize long userId, @Validated @RequestBody PostStoreRequest request, BindingResult bindingResult) {
    log.info("[StoreController.register]");
    if (bindingResult.hasErrors()) {
      throw new StoreException(INVALID_STORE_VALUE, getErrorMessages(bindingResult));
    }
    storeService.register(request, userId);
    return new BaseResponse<>(null);
  }

  /**
   * 가게 상세정보 조회
   */
  @GetMapping("/{storeId}")
  public BaseResponse<GetStoreResponse> getStoreInfo(@PathVariable long storeId) {
    log.info("[StoreController.getStoreInfo]");
    return new BaseResponse<>(storeService.getStoreInfo(storeId));
  }

  /**
   * 가게 정보 수정
   */
  @PatchMapping("/{storeId}/info")
  public BaseResponse<Object> update(@PreAuthorize long userId, @PathVariable long storeId, @Validated @RequestBody PatchStoreRequest request, BindingResult bindingResult) {
    log.info("[StoreController.update]");
    if (bindingResult.hasErrors()) {
      throw new StoreException(INVALID_STORE_VALUE, getErrorMessages(bindingResult));
    }
    storeService.update(userId, storeId, request);
    return new BaseResponse<>(null);
  }

  /**
   * 전체 가게 조회
   */
  @GetMapping("")
  public BaseResponse<List<GetStoreResponse>> getStoreList(Pageable pageable) {
    log.info("[StoreController.getStoreList]");
    System.out.println("lastIdx = " + pageable);
    return new BaseResponse<>(storeService.getStoreList(pageable));
  }
}