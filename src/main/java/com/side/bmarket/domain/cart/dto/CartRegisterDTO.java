package com.side.bmarket.domain.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CartRegisterDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class request {
        private Long productID;
        private int quantity;


    }

}
