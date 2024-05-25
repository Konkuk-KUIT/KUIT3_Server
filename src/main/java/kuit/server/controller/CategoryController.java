package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.category.response.GetCategoryResponse;
import kuit.server.dto.store.response.GetStoreResponse;
import kuit.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/categorys")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 상점 조회
     */
    @GetMapping("/{categoryId}")
    public BaseResponse<GetCategoryResponse> getUserById(
            @PathVariable long categoryId) {
        log.info("[StoreController.getUserById]");
        return new BaseResponse<>(categoryService.findCategoryResponseById(categoryId));

    }
}
