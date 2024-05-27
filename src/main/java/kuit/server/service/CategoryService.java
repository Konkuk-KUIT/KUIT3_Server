package kuit.server.service;

import kuit.server.dao.CategoryDao;
import kuit.server.domain.Category;
import kuit.server.domain.Store;
import kuit.server.dto.category.response.GetCategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryDao categoryDao;

    public Category findOneById(Long id){
        log.info("CategoryService.findOneById");
        return categoryDao.findById(id);
    }
    public GetCategoryResponse findCategoryResponseById(Long id){
        log.info("CategoryService.findCategoryResponseById");
        return GetCategoryResponse.of(findOneById(id));
    }
}
