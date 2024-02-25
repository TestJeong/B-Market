package com.side.bmarket.domain.cart.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateCartItemRequestDto {
    @Schema(description = "장바구니 상품Id")
    private final Long cartItemId;

    @Schema(description = "수량")
    private final int quantity;

    @Builder
    public UpdateCartItemRequestDto(Long cartItemId, int quantity) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }
}
