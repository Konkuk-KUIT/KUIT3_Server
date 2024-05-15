package kuit.server.service;

import kuit.server.dao.CategoryDao;
import kuit.server.dto.category.CategoryResponse;
import kuit.server.dto.category.CategoryStoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao;

    public List<CategoryResponse> getCategories(){
        return categoryDao.getAllCategories();
    }

    public List<CategoryStoreResponse> getStore(Long categoryId,int minOrderFee) {
        return categoryDao.getStores(categoryId,minOrderFee);
    }
}
