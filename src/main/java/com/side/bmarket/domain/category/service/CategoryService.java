package com.side.bmarket.domain.category.service;

import com.side.bmarket.domain.category.entity.Categorys;
import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Categorys> findAllCateogry() {
        return categoryRepository.findAllCategory();
    }

    @Transactional(readOnly = true)
    public List<SubCategorys> findBySubCategory(Long categoryID) {
        return categoryRepository.findSubCategoryByCategory(categoryID);
    }
}
