package com.side.bmarket.domain.cart.support;

import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.user.entity.Users;

public class CartFixture {
    public static Carts createCart(Users user) {
        return Carts.builder()
                .users(user)
                .build();

    }
}
