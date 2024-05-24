package kuit.server.service;

import kuit.server.common.exception.MenuException;
import kuit.server.common.exception.RestaurantException;
import kuit.server.dao.MenuDao;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.menu.PostMenuResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_MENU_STATUS;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_RESTAURANT_VALUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuDao menuDao;

    public List<GetMenuResponse> getMenus(long restaurantId){
        log.info("[MenuService.getMenus]");
        checkRestaurantId(restaurantId);
        return menuDao.getMenus(restaurantId);
    }
    public PostMenuResponse addMenu(PostMenuRequest postMenuRequest, long restaurantId){
        log.info("[MenuService.addMenu]");
        checkRestaurantId(restaurantId);
        validateMenuStatus(postMenuRequest.getMenu_status());
        long menu_id = menuDao.addMenu(postMenuRequest, restaurantId);
        return new PostMenuResponse(menu_id);
    }
    public void checkRestaurantId(long restaurantId){
        if(!menuDao.isValidRestaurantId(restaurantId)){
            throw new RestaurantException(INVALID_RESTAURANT_VALUE);
        }
    }
    public void validateMenuStatus(String status){
        if (!status.equals("Available") || !status.equals("Not Available")){
            throw new MenuException(INVALID_MENU_STATUS);
        }
    }
}
