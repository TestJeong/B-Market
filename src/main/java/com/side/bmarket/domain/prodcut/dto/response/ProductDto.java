package com.side.bmarket.domain.prodcut.dto.response;

import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.Getter;


@Getter
public class ProductDto {

    private Long id;
    private String productName;
    private int productPrice;
    private int discountRate;
    private int discountPrice;
    private int quantity;

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



