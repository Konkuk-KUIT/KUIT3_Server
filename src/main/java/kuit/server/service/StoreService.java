package kuit.server.service;

import kuit.server.common.exception.DatabaseException;
import kuit.server.dao.StoreDao;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PostStoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreDao storeDao;

    public long resgisterStore(PostStoreRequest storeRequest){
        return storeDao.createStore(storeRequest);
    }

    public GetStoreResponse getStoreById(long storeId){
        return storeDao.findStoreById(storeId);
    }

    public void modifyStoreStatus_deleted(long storeId) {
        int affectedRows = storeDao.modifyStoreStatus_deleted(storeId);

    }

    public List<GetStoreResponse> getAllStores() {
        return storeDao.findAllStores();
    }

    public void modifyStoreStatus_dormant(long storeId) {
        int affectedRows = storeDao.modifyStoreStatus_dormant(storeId);
    }

}