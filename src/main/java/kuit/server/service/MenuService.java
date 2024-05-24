package kuit.server.service;

import kuit.server.dao.MenuDao;
import kuit.server.dto.menu.GetMenuResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuDao menuDao;

    public List<GetMenuResponse> getMenus(long restaurantId){
        return menuDao.getMenus(restaurantId);
    }
}
