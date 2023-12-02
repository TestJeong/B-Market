package com.side.bmarket.domain.category.api;

import com.side.bmarket.domain.category.dto.CategoryDTO;
import com.side.bmarket.domain.category.dto.SubCategoryDTO;
import com.side.bmarket.domain.category.entity.Categorys;
import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category")
    public List<CategoryDTO.response> getCategoryList() {
        List<Categorys> findAllCategory = categoryService.findAllCateogry();
        return findAllCategory.stream()
                .map(CategoryDTO.response::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/subcategory")
    public List<SubCategoryDTO.response> getSubCategoryList(@RequestParam("categoryID") Long categoryID) {
        List<SubCategorys> getSubCategory = categoryService.findBySubCategory(categoryID);
        return getSubCategory.stream()
                .map(SubCategoryDTO.response::new)
                .collect(Collectors.toList());

    }

}
