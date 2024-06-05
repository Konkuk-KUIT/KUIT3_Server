package kuit.server.controller;

import java.util.List;
import kuit.server.dto.store.GetStoreMenuResponse;
import kuit.server.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/{storeId}/menus")
    public List<GetStoreMenuResponse> getStoreMenus(@PathVariable long storeId) {
        return storeService.getMenu(storeId);
    }
}
