package com.side.bmarket.domain.prodcut.dto.response;

import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.Getter;


@Getter
public class ProductDto {

    private final Long id;
    private final String productName;
    private final int productPrice;
    private final int discountRate;
    private final int discountPrice;
    private final int quantity;

    public ProductDto(Products product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.discountRate = product.getDiscountRate();
        this.discountPrice = product.getProductPrice() - product.getDiscountPrice();
        this.quantity = product.getQuantity();
    }

    public static ProductDto of(Products products) {
        return new ProductDto(products);

    }
}



