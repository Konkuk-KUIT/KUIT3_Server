package kuit.server.controller;

import kuit.server.common.response.BaseResponse;
import kuit.server.dto.category.CategoryResponse;
import kuit.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    // 카테고리 목록 조회하기
    @GetMapping("")
    public BaseResponse<List<CategoryResponse>> getCategories(){
        return new BaseResponse<>(categoryService.getCategories());
    }


}
