package com.side.bmarket.domain.category.service;

import com.side.bmarket.domain.category.dto.response.CategoryDto;
import com.side.bmarket.domain.category.dto.response.SubCategoryDto;
import com.side.bmarket.domain.category.repository.CategoryRepository;
import com.side.bmarket.domain.category.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDto> findAllCategory() {
        return categoryRepository.findAll().stream()
                .map(item -> new CategoryDto(item.getId(), item.getCategoryName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SubCategoryDto> findBySubCategory(Long categoryId) {
        return subCategoryRepository.findByCategoryId(categoryId).stream()
                .map(item -> new SubCategoryDto(item.getId(), item.getSubCategoryName()))
                .collect(Collectors.toList());
    }
}
