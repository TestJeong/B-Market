package com.side.bmarket.domain.cart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveCartRequestDto {
    private Long productId;
    private int quantity;

}
