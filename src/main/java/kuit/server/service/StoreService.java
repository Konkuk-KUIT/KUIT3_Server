package kuit.server.service;

import kuit.server.common.exception.DatabaseException;
import kuit.server.common.exception.StoreException;
import kuit.server.dao.StoreDao;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PatchStoreRequest;
import kuit.server.dto.store.PostStoreRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

  private final StoreDao storeDao;

  public void register(PostStoreRequest request) {
    log.info("[StoreService.register]");

    validateBusinessNumber(request.getBusinessNumber());
    storeDao.save(request);
  }

  public void update(long storeId, PatchStoreRequest request) {
    log.info("[StoreService.update]");
    int affectedRows = storeDao.update(storeId, request);
    if (affectedRows != 1) {
      throw new DatabaseException(DATABASE_ERROR);
    }
  }

  public GetStoreResponse getStoreInfo(long storeId) {
    GetStoreResponse response = storeDao.findById(storeId);
    if (response == null) {
      throw new StoreException(STORE_NOT_FOUND);
    }
    return response;
  }

  public List<GetStoreResponse> getStoreList() {
    log.info("[MenuService.getMenuList]");
    return storeDao.getStoreList();
  }

  private void validateBusinessNumber(String businessNumber) {
    if (storeDao.hasDuplicatedBusinessNumber(businessNumber)) {
      throw new StoreException(DUPLICATE_BUSINESS_NUMBER);
    }
  }
}