package com.side.bmarket.domain.category.support;

import com.side.bmarket.domain.category.entity.Categorys;

public class CategoryFixture {
    public static Categorys createCategory(String categoryName) {
        return Categorys.builder()
                .categoryName(categoryName)
                .build();
    }
}
