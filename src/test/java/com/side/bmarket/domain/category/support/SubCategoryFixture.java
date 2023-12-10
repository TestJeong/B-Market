package com.side.bmarket.domain.category.support;

import com.side.bmarket.domain.category.entity.Categorys;
import com.side.bmarket.domain.category.entity.SubCategorys;

public class SubCategoryFixture {
    public static SubCategorys createSubCategory(Categorys category, String subCategoryName) {
        return SubCategorys.builder()
                .category(category)
                .subCategoryName(subCategoryName)
                .build();

    }
}
