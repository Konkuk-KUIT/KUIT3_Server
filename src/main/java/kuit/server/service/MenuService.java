package kuit.server.service;

import kuit.server.common.exception.MenuException;
import kuit.server.common.exception.UserException;
import kuit.server.domain.Menu;
import kuit.server.domain.Store;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.repository.MenuRepository;
import kuit.server.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {

  private final StoreRepository storeRepository;
  private final MenuRepository menuRepository;

  public void register(long userId, long storeId, PostMenuRequest request) {
    log.info("[MenuService.register]");
    Store store = validateStoreId(storeId);
    validateUser(userId, store.getUserId());
    validateMenuName(request.getName());
    menuRepository.save(Menu.from(request, storeId));
  }

  public void update(long userId, long storeId, long menuId, PostMenuRequest request) {
    log.info("[MenuService.update]");

    Store store = validateStoreId(storeId);
    validateUser(userId, store.getUserId());
    Menu menu = menuRepository.findByStoreIdAndMenuId(storeId, menuId)
      .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

    menu.setName(request.getName());
    menu.setPrice(request.getPrice());
    menu.setDescription(request.getDescription());
    menuRepository.save(menu);
  }

  public GetMenuResponse getMenuInfo(long storeId, long menuId) {
    log.info("[MenuService.getMenuInfo]");
    validateStoreId(storeId);
    return GetMenuResponse.from(menuRepository.findByStoreIdAndMenuId(storeId, menuId)
      .orElseThrow(() -> new MenuException(MENU_NOT_FOUND)));
  }

  public List<GetMenuResponse> getMenuList(long storeId, Pageable pageable) {
    log.info("[MenuService.getMenuList]");
    List<GetMenuResponse> menuList = new ArrayList<>();
    menuRepository.findAllByStoreId(storeId, pageable).forEach(x ->
      menuList.add(GetMenuResponse.from(x)));
    return menuList;
  }

  private Store validateStoreId(long storeId) {
    return storeRepository.findById(storeId)
      .orElseThrow(() -> new MenuException(INVALID_STORE_VALUE));
  }

  private void validateUser(long userId, long savedUserId) {
    if (userId != savedUserId) {
      throw new UserException(BAD_REQUEST);
    }
  }

  private void validateMenuName(String menuName) {
    if (menuRepository.existsByName(menuName)) {
      throw new MenuException(DUPLICATE_MENU_NAME);
    }
  }
}