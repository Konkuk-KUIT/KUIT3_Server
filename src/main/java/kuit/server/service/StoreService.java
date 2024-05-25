package kuit.server.service;

import kuit.server.common.exception.StoreException;
import kuit.server.dao.StoreDao;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PostStoreRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DUPLICATE_STORENAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreDao storeDao;

    public long registerStore(PostStoreRequest storeRequest) {
        if (storeDao.hasDuplicateStoreName(storeRequest.getName())) {
            throw new StoreException(DUPLICATE_STORENAME, "가게 이름이 중복됩니다.");
        }
        return storeDao.createStore(storeRequest);
    }

    public GetStoreResponse getStoreById(long storeId) {
        log.info("[StoreService.getStoreById]", storeId);
        return storeDao.findStoreById(storeId);
    }

    public List<GetStoreResponse> getAllStores() {
        return storeDao.findAllStores();
    }

    private void validateStoreName(String storename) {
        if (storeDao.hasDuplicateStoreName(storename)) {
            throw new StoreException(DUPLICATE_STORENAME);
        }
    }
}
