package com.side.bmarket.domain.prodcut.dto;

import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.Getter;

@Getter
public class ProductDTO {

    @Getter
    public static class response {
        private Long id;
        private String productName;
        private int productPrice;
        private int discountRate;
        private int discountPrice;
        private int quantity;

        public response(Products product) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.productPrice = product.getProductPrice();
            this.discountRate = product.getDiscountRate();
            this.discountPrice = product.getProductPrice() - product.getDiscountPrice();
            this.quantity = product.getQuantity();
        }
    }


}
