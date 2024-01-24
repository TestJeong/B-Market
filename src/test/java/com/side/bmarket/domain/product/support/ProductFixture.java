package com.side.bmarket.domain.product.support;

import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.support.SubCategoryFixture;
import com.side.bmarket.domain.prodcut.entity.Products;

public class ProductFixture {
    public static Products createProduct(String productName, int productPrice, int discountPrice, int discountRate, int quantity) {
        return Products.builder()
                .subCategory(SubCategoryFixture.createSubCategory())
                .productName(productName)
                .productPrice(productPrice)
                .quantity(quantity)
                .discountRate(discountRate)
                .discountPrice(discountPrice)
                .build();
    }

    public static Products createProductWithSubCategory(String productName, SubCategorys subCategory, int productPrice, int discountPrice, int discountRate, int quantity) {
        return Products.builder()
                .subCategory(subCategory)
                .productName(productName)
                .productPrice(productPrice)
                .quantity(quantity)
                .discountRate(discountRate)
                .discountPrice(discountPrice)
                .build();
    }
}
