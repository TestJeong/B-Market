package com.side.bmarket.domain.category.dto.response;

import lombok.Getter;

@Getter
public class CategoryDto {
    private final Long id;
    private final String categoryName;

    public CategoryDto(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
