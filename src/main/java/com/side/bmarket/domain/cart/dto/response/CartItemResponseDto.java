package com.side.bmarket.domain.cart.dto.response;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartItemResponseDto {
    private final Long cartItemId;
    private final Long productId;
    private final String productName;
    private final int quantity;
    private final int productPrice;
    private final int discountRate;
    private final int discountPrice;

    @Builder
    public CartItemResponseDto(Long cartItemId, Long productId, String productName, int quantity, int productPrice, int discountRate, int discountPrice) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.discountRate = discountRate;
        this.discountPrice = discountPrice;
    }

    public static CartItemResponseDto fromCartItem(CartItems cartItem) {
        Products product = cartItem.getProduct();

        return CartItemResponseDto.builder()
                .cartItemId(cartItem.getId())
                .productId(cartItem.getProduct().getId())
                .quantity(cartItem.getProductQuantity())
                .discountRate(product.getDiscountRate())
                .productPrice(product.getProductPrice())
                .productName(product.getProductName())
                .discountPrice(product.getProductPrice() - product.getDiscountPrice())
                .build();

    }
}
