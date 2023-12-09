package com.side.bmarket.domain.cart.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateCartItemRequestDto {
    private Long cartItemId;
    private int quantity;

    @Builder
    public UpdateCartItemRequestDto(Long cartItemId, int quantity) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }
}
