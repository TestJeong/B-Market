package com.side.bmarket.domain.cart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveCartRequestDto {
    private Long productID;
    private int quantity;

}
