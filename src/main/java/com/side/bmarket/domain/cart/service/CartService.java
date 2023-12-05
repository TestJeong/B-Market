package com.side.bmarket.domain.cart.service;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.cart.repository.CartRepository;
import com.side.bmarket.domain.prodcut.dto.ProductDTO;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    @Transactional
    public void saveCartItem(Long productID, int quantity) {
        Carts cart = cartRepository.findCartByUser(1L);
        Products product = productService.getProduct(productID);
        List<CartItems> cartItemByCart = cartRepository.findCartItemByCart(cart.getId());

        Optional<Long> isValuePresent = cartItemByCart.stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productID))
                .map(CartItems::getId)
                .findFirst();


        if (isValuePresent.isPresent()) {
            updateCartItemQuantity(isValuePresent.get(), quantity);
        } else {

            CartItems cartItem = CartItems.builder()
                    .cart(cart)
                    .product(product)
                    .productQuantity(quantity)
                    .build();

            cartRepository.saveCartItem(cartItem);
        }


    }

    private void updateCartItemQuantity(Long cartItemID, int quantity) {
        CartItems cartItem = cartRepository.findByCartItem(cartItemID);
        cartItem.updateQuantity(cartItem.getProductQuantity() + quantity);


    }

    public List<ProductDTO.response> getCartList() {
        Carts cart = cartRepository.findCartByUser(1L);
        List<CartItems> cartItemByCart = cartRepository.findCartItemByCart(cart.getId());
        return cartItemByCart.stream()
                .map(i -> new ProductDTO.response(i.getProduct()))
                .collect(Collectors.toList());

    }
    //    * 최대 구매 수량 검증 필요?
    //    * 총 금액 계산
    //    * 무료 배송 인지? 배송비 계싼
}
