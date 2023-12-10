package com.side.bmarket.domain.product.support;

import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.prodcut.entity.Products;

public class ProductFixture {
    public static Products createProduct(SubCategorys subCategory, String productName, int productPrice, int discountPrice, int discountRate, int quantity) {
        Products product = Products.builder()
                .subCategory(subCategory)
                .productName(productName)
                .productPrice(productPrice)
                .quantity(quantity)
                .discountRate(discountRate)
                .discountPrice(discountPrice)
                .build();
        return product;
    }
}
