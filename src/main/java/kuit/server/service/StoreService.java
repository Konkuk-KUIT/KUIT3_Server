package kuit.server.service;

import kuit.server.common.exception.StoreException;
import kuit.server.common.exception.UserException;
import kuit.server.domain.Store;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PatchStoreRequest;
import kuit.server.dto.store.PostStoreRequest;
import kuit.server.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

  private final StoreRepository storeRepository;

  public void register(PostStoreRequest request, long userId) {
    log.info("[StoreService.register]");

    validateBusinessNumber(request.getBusinessNumber());
    storeRepository.save(Store.from(request, userId));
  }

  public void update(long userId, long storeId, PatchStoreRequest request) {
    log.info("[StoreService.update]");
    Store store = validateStoreId(storeId);
    validateUser(userId, store.getUserId());
    storeRepository.save(Store.from(request));
  }

  public GetStoreResponse getStoreInfo(long storeId) {
    return GetStoreResponse.from(validateStoreId(storeId));
  }

  public List<GetStoreResponse> getStoreList(Pageable pageable) {
    log.info("[MenuService.getMenuList]");
    List<GetStoreResponse> storeList = new ArrayList<>();
    storeRepository.findAllByStatusIs(pageable, "active").forEach(x ->
      storeList.add(GetStoreResponse.from(x)));
    return storeList;
  }

  private void validateUser(long userId, long savedUserId) {
    if (userId != savedUserId) {
      throw new UserException(BAD_REQUEST);
    }
  }

  public Store validateStoreId(long storeId) {
    return storeRepository.findById(storeId)
      .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
  }

  private void validateBusinessNumber(String businessNumber) {
    if (storeRepository.existsByBusinessNumber(businessNumber)) {
      throw new StoreException(DUPLICATE_BUSINESS_NUMBER);
    }
  }
}