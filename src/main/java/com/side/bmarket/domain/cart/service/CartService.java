package com.side.bmarket.domain.cart.service;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.cart.repository.CartRepository;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    @Transactional
    public void saveCartItem(Long productID, int quantity) {
        Carts cart = cartRepository.findCartByUser(1L);
        Products product = productService.getProduct(productID);


        CartItems cartItem = CartItems.builder()
                .cart(cart)
                .product(product)
                .productQuantity(quantity)
                .build();

        cartRepository.saveCartItem(cartItem);
    }

    public void getCartList () {
        Carts cart = cartRepository.findCartByUser(1L);
        List<CartItems> cartItemByCart = cartRepository.findCartItemByCart(cart.getId());

    }
    //    * 최대 구매 수량 검증 필요?
    //    * 총 금액 계산
    //    * 무료 배송 인지? 배송비 계싼
    //    * 수량 업데이트
}
