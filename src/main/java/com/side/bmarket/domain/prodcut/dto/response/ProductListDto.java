package com.side.bmarket.domain.prodcut.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductListDto {
    private Long id;
    private String subCategoryName;
    private List<ProductDto> product;

    @Builder
    public ProductListDto(Long id, String subCategoryName, List<ProductDto> product) {
        this.id = id;
        this.subCategoryName = subCategoryName;
        this.product = product;
    }

    public static ProductListDto of(Long id, String subCategoryName, List<ProductDto> product) {
        return ProductListDto.builder()
                .id(id)
                .subCategoryName(subCategoryName)
                .product(product)
                .build();
    }
}
