package com.side.bmarket.domain.category.dto;

import com.side.bmarket.domain.category.entity.SubCategorys;
import lombok.Getter;

@Getter
public class SubCategoryDTO {
    @Getter
    public static class response {
        private Long id;
        private String subCategoryName;

        public response(SubCategorys subCategory) {
            this.id = subCategory.getId();
            this.subCategoryName = subCategory.getSubCategoryName();
        }
    }
}
