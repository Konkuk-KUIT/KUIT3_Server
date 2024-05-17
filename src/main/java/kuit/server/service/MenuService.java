package kuit.server.service;

import kuit.server.common.exception.DatabaseException;
import kuit.server.common.exception.MenuException;
import kuit.server.dao.MenuDao;
import kuit.server.dao.StoreDao;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {

  private final MenuDao menuDao;
  private final StoreDao storeDao;

  public void register(long storeId, PostMenuRequest request) {
    log.info("[MenuService.register]");
    validateStoreId(storeId);
    validateMenuName(request.getName());
    menuDao.save(storeId, request);
  }

  public void update(long storeId, PostMenuRequest request) {
    log.info("[MenuService.update]");
    int affectedRows = menuDao.update(storeId, request);
    if (affectedRows != 1) {
      throw new DatabaseException(DATABASE_ERROR);
    }
  }

  public GetMenuResponse getMenuInfo(long storeId, long menuId) {
    log.info("[MenuService.getMenuInfo]");
    validateStoreId(storeId);
    GetMenuResponse response = menuDao.findById(menuId);
    if (response == null) {
      throw new MenuException(MENU_NOT_FOUND);
    }
    return response;
  }

  public List<GetMenuResponse> getMenuList(long storeId) {
    log.info("[MenuService.getMenuList]");
    return menuDao.getMenuList(storeId);
  }

  private void validateStoreId(long storeId) {
    if (!storeDao.validateStoreId(storeId)) {
      throw new MenuException(INVALID_STORE_VALUE);
    }
  }

  private void validateMenuName(String menuName) {
    if (menuDao.hasDuplicatedMenuName(menuName)) {
      throw new MenuException(DUPLICATE_MENU_NAME);
    }
  }
}