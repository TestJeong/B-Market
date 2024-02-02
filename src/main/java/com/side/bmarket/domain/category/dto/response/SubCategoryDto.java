package com.side.bmarket.domain.category.dto.response;

import lombok.Getter;

@Getter
public class SubCategoryDto {
    private final Long id;
    private final String subCategoryName;

    public SubCategoryDto(Long id, String subCategoryName) {
        this.id = id;
        this.subCategoryName = subCategoryName;
    }
}
