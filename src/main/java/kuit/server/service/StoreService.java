package kuit.server.service;

import kuit.server.common.exception.StoreException;
import kuit.server.domain.Store;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PatchStoreRequest;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DUPLICATE_BUSINESS_NUMBER;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.STORE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

  private final StoreRepository storeRepository;

  public void register(PostStoreRequest request) {
    log.info("[StoreService.register]");

    validateBusinessNumber(request.getBusinessNumber());
    storeRepository.save(Store.from(request));
  }

  public void update(long storeId, PatchStoreRequest request) {
    log.info("[StoreService.update]");
    Store store = storeRepository.findById(storeId)
      .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
    storeRepository.save(Store.from(request));
  }

  public GetStoreResponse getStoreInfo(long storeId) {
    return GetStoreResponse.from(storeRepository.findById(storeId)
      .orElseThrow(() -> new StoreException(STORE_NOT_FOUND)));
  }

  public List<GetStoreResponse> getStoreList() {
    log.info("[MenuService.getMenuList]");
    List<GetStoreResponse> storeList = new ArrayList<>();
    storeRepository.findAll().forEach(x ->
      storeList.add(GetStoreResponse.from(x)));
    return storeList;
  }

  private void validateBusinessNumber(String businessNumber) {
    if (storeRepository.existsByBusinessNumber(businessNumber)) {
      throw new StoreException(DUPLICATE_BUSINESS_NUMBER);
    }
  }
}