package kuit.server.service;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.EMPTY_MENU;

import java.util.List;
import kuit.server.common.exception.UserException;
import kuit.server.dao.StoreDao;
import kuit.server.dto.store.GetStoreMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreDao storeDao;

    public List<GetStoreMenuResponse> getMenu(long storeId) {
        List<GetStoreMenuResponse> menus = storeDao.getMenus(storeId);

        if(menus.isEmpty()) {
            throw new UserException(EMPTY_MENU);
        }

        return menus;
    }
}
