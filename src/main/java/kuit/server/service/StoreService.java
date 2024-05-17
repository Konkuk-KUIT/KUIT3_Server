package kuit.server.service;

import kuit.server.dao.StoreDao;
import kuit.server.dto.store.GetStoreResponse;
import kuit.server.dto.store.PostStoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<GetStoreResponse> getAllStores() {
        return storeDao.findAllStores();
    }
}
