package com.side.bmarket.domain.cart.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CartListResponseDto {
    private final int currentPage;
    private final int totalPrice;
    private final int totalQuantity;
    private final int deliveryFee;
    private final List<CartItemResponseDto> product;

    @Builder
    public CartListResponseDto(int currentPage, int totalPrice, int totalQuantity, int deliveryFee, List<CartItemResponseDto> product) {
        this.currentPage = currentPage;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.deliveryFee = deliveryFee;
        this.product = product;
    }

}

