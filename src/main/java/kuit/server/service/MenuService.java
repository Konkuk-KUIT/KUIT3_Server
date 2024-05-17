package kuit.server.service;

import kuit.server.common.exception.DatabaseException;
import kuit.server.dao.MenuDao;
import kuit.server.dao.UserDao;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.dto.menu.PostMenuResponse;
import kuit.server.dto.user.GetUserResponse;
import kuit.server.dto.user.PostUserRequest;
import kuit.server.dto.user.PostUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.DATABASE_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuDao menuDao;

    public PostMenuResponse registerMenu(PostMenuRequest postMenuRequest) {
        log.info("[MenuService.registerMenu]");

        long menuId = menuDao.createMenu(postMenuRequest);

        return new PostMenuResponse(menuId);
    }

    public void modifyPrice(long menuId, int price) {
        log.info("[MenuService.modifyPrice]");

        int affectedRows = menuDao.modifyPrice(menuId, price);
        if (affectedRows != 1) {
            throw new DatabaseException(DATABASE_ERROR);
        }
    }
    public List<GetMenuResponse> getMenus(String name, String category, String price) {
        log.info("[MenuService.getMenus]");
        return menuDao.getMenus(name, category, price);
    }

    public void modifyMenuStatus_deleted(long menuId) {
        log.info("[MenuService.modifyMenuStatus_deleted]");

        int affectedRows = menuDao.modifyMenuStatus_deleted(menuId);
        if (affectedRows != 1) {
            throw new DatabaseException(DATABASE_ERROR);
        }
    }

}
