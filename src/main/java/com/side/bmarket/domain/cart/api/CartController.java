package com.side.bmarket.domain.cart.api;

import com.side.bmarket.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add/cart/product")
    public void addCartProduct() {
        cartService.saveCartItem(2L, 1);
    }
}
