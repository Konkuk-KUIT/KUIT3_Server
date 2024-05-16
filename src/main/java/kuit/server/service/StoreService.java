package kuit.server.service;


import kuit.server.dao.StoreDao;
import kuit.server.domain.Store;
import kuit.server.dto.store.response.GetStoreResponse;
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
        return GetStoreResponse.of(findOneById(id));
    }
}
