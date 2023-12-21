package com.side.bmarket.domain.category.support;

import com.side.bmarket.domain.category.entity.Categorys;
import com.side.bmarket.domain.category.entity.SubCategorys;

public class SubCategoryFixture {
    public static SubCategorys createSubCategory() {
        return SubCategorys.builder()
                .category(CategoryFixture.createCategory("대카테고리"))
                .subCategoryName("서브 카테고리")
                .build();

    }
}
