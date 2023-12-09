package com.side.bmarket.domain.cart.service;

import com.side.bmarket.domain.cart.dto.response.CartListResponseDto;
import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.cart.exception.NotFoundCartItemException;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.cart.repository.CartRepository;
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
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public void saveCartItem(Long productID, int quantity) {
        Carts cart = cartRepository.findByUserId(1L);
        Products product = productService.getProduct(productID);
        List<CartItems> cartItemByCart = cartItemRepository.findByCartId(cart.getId());

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
            cartItemRepository.save(cartItem);
        }
    }

    public void deleteCartItem(Long cartItemId) {
        CartItems cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new NotFoundCartItemException("해당 cartItem이 없습니다."));
        cartItemRepository.delete(cartItem);
    }

    public int calculateTotalPrice(Long cartId) {
        List<CartItems> cartList = cartItemRepository.findByCartId(cartId);
        return cartList.stream()
                .mapToInt(i -> (i.getProduct().getProductPrice() - i.getProduct().getDiscountPrice()) * i.getProductQuantity())
                .sum();
    }

    private int calculateDeliveryFee(Long cartId) {
        int minimumPrice = 15000;
        int totalPrice = calculateTotalPrice(cartId);

        if (totalPrice > minimumPrice) return 0;
        else return 3000;
    }

    public void updateCartItemQuantity(Long cartItemID, int quantity) {
        CartItems cartItem = cartItemRepository.findById(cartItemID)
                .orElseThrow(() -> new NotFoundCartItemException("해당 cartItem이 없습니다."));

        cartItem.updateQuantity(cartItem.getProductQuantity() + quantity);
    }

    @Transactional(readOnly = true)
    public CartListResponseDto findCartItemByUser() {
        Carts cart = cartRepository.findByUserId(1L);
        List<CartItems> byCartId = cartItemRepository.findByCartId(cart.getId());

        int totalPrice = calculateTotalPrice(cart.getId());
        int deliveryFee = calculateDeliveryFee(cart.getId());

        List<CartListResponseDto.CartItemList> result = byCartId.stream()
                .map(CartListResponseDto.CartItemList::fromCartItem)
                .collect(Collectors.toList());

        return CartListResponseDto.builder()
                .totalPrice(totalPrice)
                .totalQuantity(result.size())
                .deliveryFee(deliveryFee)
                .product(result)
                .build();
    }
}
