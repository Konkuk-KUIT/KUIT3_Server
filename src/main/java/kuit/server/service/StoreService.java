package kuit.server.service;


import kuit.server.dao.StoreDao;
import kuit.server.domain.Member;
import kuit.server.domain.Store;
import kuit.server.dto.store.request.PostStoreRequest;
import kuit.server.dto.store.response.GetStoreResponse;
import kuit.server.dto.store.response.PostStoreResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreDao storeDao;

    public Store findOneById(Long id){
        log.info("[StoreService.findOneById]");
        return storeDao.findById(id);
    }

    public GetStoreResponse findStoreResponseById(Long id){
        log.info("[StoreService.findStoreResponseById]");
        return GetStoreResponse.of(findOneById(id));
    }
    public Store createStore(Store store) {
        log.info("[StoreService.createStore]");
        storeDao.createStore(store);
        return store;
    }

    public PostStoreResponse createStoreByPostStoreResponse(PostStoreRequest postStoreRequest){
        log.info("[StoreService.createStoreByPostStoreResponse]");
        Store store = postStoreRequest.toEntity();
        storeDao.createStore(store);
        return PostStoreResponse.of(store);
    }


}
