package com.side.bmarket.domain.cart.dto.response;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CartListResponseDto {
    private final int currentPage;
    private final int totalPrice;
    private final int totalQuantity;
    private final int deliveryFee;
    private final List<CartItemList> product;

    @Builder
    public CartListResponseDto(int currentPage, int totalPrice, int totalQuantity, int deliveryFee, List<CartItemList> product) {
        this.currentPage = currentPage;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.deliveryFee = deliveryFee;
        this.product = product;
    }

    @Getter
    public static class CartItemList {
        private final Long cartItemId;
        private final String productName;
        private final int quantity;
        private final int productPrice;
        private final int discountRate;
        private final int discountPrice;

        @Builder
        public CartItemList(Long cartItemId, String productName, int quantity, int productPrice, int discountRate, int discountPrice) {
            this.cartItemId = cartItemId;
            this.productName = productName;
            this.quantity = quantity;
            this.productPrice = productPrice;
            this.discountRate = discountRate;
            this.discountPrice = discountPrice;
        }

        public static CartItemList fromCartItem(CartItems cartItem) {
            Products product = cartItem.getProduct();

            return CartItemList.builder()
                    .cartItemId(cartItem.getId())
                    .quantity(cartItem.getProductQuantity())
                    .discountRate(product.getDiscountRate())
                    .productPrice(product.getProductPrice())
                    .productName(product.getProductName())
                    .discountPrice(product.getProductPrice() - product.getDiscountPrice())
                    .build();

        }
    }
}

