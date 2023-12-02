package com.side.bmarket.domain.prodcut.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductByCategoryDTO {

    @Getter
    public static class response {
        private Long id;
        private String subCategoryName;
        private List<ProductDTO.response> product;

        public response(Long id, String subCategoryName, List<ProductDTO.response> product) {
            this.id = id;
            this.subCategoryName = subCategoryName;
            this.product = product;
        }
    }
}
