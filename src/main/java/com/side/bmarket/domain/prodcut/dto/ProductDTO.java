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
        private int discountPrice;
        private int quantity;

        public response(Products product) {
            this.id = product.getId();
            this.productName = product.getProductName();
            this.productPrice = product.getProductPrice();
            this.discountPrice = product.getDiscountPrice();
            this.quantity = product.getQuantity();
        }
    }


}
