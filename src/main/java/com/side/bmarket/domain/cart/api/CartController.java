package com.side.bmarket.domain.cart.api;

import com.side.bmarket.domain.cart.dto.CartRegisterDTO;
import com.side.bmarket.domain.cart.service.CartService;
import com.side.bmarket.domain.prodcut.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart/list")
    public List<ProductDTO.response> getCartList() {
        return cartService.getCartList();

    }

    @PostMapping("/add/cart/product")
    public void addCartProduct(@RequestBody CartRegisterDTO.request requestDTO) {
        cartService.saveCartItem(requestDTO.getProductID(), requestDTO.getQuantity());
    }
}
