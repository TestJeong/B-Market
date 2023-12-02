package com.side.bmarket.domain.category.dto;

import com.side.bmarket.domain.category.entity.Categorys;
import lombok.Getter;

@Getter
public class CategoryDTO {

    @Getter
    public static class response {
        private Long id;
        private String categoryName;

        public response(Categorys category) {
            this.id = category.getId();
            this.categoryName = category.getCategoryName();
        }
    }
}
