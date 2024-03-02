package com.side.bmarket.domain.prodcut.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductListDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String subCategoryName;
    private final int currentPage;
    private final boolean hasNextPage;
    private final List<ProductDto> product;

    @Builder
    public ProductListDto(Long id, String subCategoryName, Boolean hasNextPage, int currentPage, List<ProductDto> product) {
        this.id = id;
        this.subCategoryName = subCategoryName;
        this.currentPage = currentPage;
        this.hasNextPage = hasNextPage;
        this.product = product;
    }

    public static ProductListDto of(Long id, String subCategoryName, Boolean hasNextPage, int currentPage, List<ProductDto> product) {
        return ProductListDto.builder()
                .id(id)
                .hasNextPage(hasNextPage)
                .subCategoryName(subCategoryName)
                .currentPage(currentPage)
                .product(product)
                .build();
    }
}
