package com.side.bmarket.domain.cart.support;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.prodcut.entity.Products;

public class CartItemFixture {
    public static CartItems createCartItem(Carts cart, Products product, int quantity) {
        CartItems cartItem = CartItems.builder()
                .cart(cart)
                .product(product)
                .productQuantity(quantity)
                .build();
        return cartItem;
    }
}
