package com.side.bmarket.domain.cart.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateCartItemRequestDto {
    private final Long cartItemId;
    private final int quantity;

    @Builder
    public UpdateCartItemRequestDto(Long cartItemId, int quantity) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }
}
