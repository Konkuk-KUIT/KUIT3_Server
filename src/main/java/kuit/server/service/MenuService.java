package kuit.server.service;

import kuit.server.dao.MenuDao;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.menu.PostMenuResponse;
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
        log.info("[MenuService.getMenus]");
        return menuDao.getMenus(restaurantId);
    }
    public PostMenuResponse addMenu(PostMenuRequest postMenuRequest, long restaurantId){
        log.info("[MenuService.addMenu]");
        long menu_id = menuDao.addMenu(postMenuRequest, restaurantId);
        return new PostMenuResponse(menu_id);
    }

}
