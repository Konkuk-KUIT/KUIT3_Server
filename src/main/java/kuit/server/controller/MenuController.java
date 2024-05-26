package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.menu.GetMenuResponse;
import kuit.server.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("")
    public BaseResponse<List<GetMenuResponse>> searchByKeyword(@RequestParam("keyword") String keyword){
        log.info("menuController.searchByKeyword :: " + keyword);
        return new BaseResponse<>(menuService.searchByKeyword(keyword));
    }
}
