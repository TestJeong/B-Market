package com.side.bmarket.domain.category.api;

import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.category.dto.response.CategoryDto;
import com.side.bmarket.domain.category.dto.response.SubCategoryDto;
import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //    카테고리 리스트 조회
    @GetMapping("/category")
    public ResponseEntityDto<List<CategoryDto>> getCategoryList() {
        return ResponseEntityDto.of(HttpStatus.OK, categoryService.findAllCategory());
    }

    //    서브 카테고리 조회
    @GetMapping("/subcategory")
    public ResponseEntityDto<List<SubCategoryDto>> getSubCategoryList(@RequestParam("categoryID") Long categoryID) {
        return ResponseEntityDto.of(HttpStatus.OK, categoryService.findBySubCategory(categoryID));
    }

}
