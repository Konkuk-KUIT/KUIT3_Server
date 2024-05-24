package kuit.server.service;

import kuit.server.common.exception.MenuException;
import kuit.server.common.exception.StoreException;
import kuit.server.domain.Menu;
import kuit.server.domain.Store;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.dto.menu.PostMenuRequest;
import kuit.server.repository.MenuRepository;
import kuit.server.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  public void register(long storeId, PostMenuRequest request) {
    log.info("[MenuService.register]");
    validateStoreId(storeId);
    validateMenuName(request.getName());
    menuRepository.save(Menu.from(request, storeId));
  }

  public void update(long storeId, PostMenuRequest request) {
    log.info("[MenuService.update]");

    Store store = storeRepository.findById(storeId)
      .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
    Menu menu = menuRepository.findByStoreIdAndName(store.getStoreId(), request.getName())
      .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));

    menu.setName(request.getName());
    menu.setPrice(request.getPrice());
    menu.setDescription(request.getDescription());
    menuRepository.save(menu);
  }

  public GetMenuResponse getMenuInfo(long storeId, long menuId) {
    log.info("[MenuService.getMenuInfo]");
    validateStoreId(storeId);
    return GetMenuResponse.from(menuRepository.findById(menuId)
      .orElseThrow(() -> new MenuException(MENU_NOT_FOUND)));
  }

  public List<GetMenuResponse> getMenuList(long storeId) {
    List<GetMenuResponse> menuList = new ArrayList<>();
    log.info("[MenuService.getMenuList]");
    menuRepository.getMenus().forEach(x ->
      menuList.add(GetMenuResponse.from(x)));
    return menuList;
  }

  private void validateStoreId(long storeId) {
    if (!storeRepository.existsById(storeId)) {
      throw new MenuException(INVALID_STORE_VALUE);
    }
  }

  private void validateMenuName(String menuName) {
    if (menuRepository.existsByName(menuName)) {
      throw new MenuException(DUPLICATE_MENU_NAME);
    }
  }
}