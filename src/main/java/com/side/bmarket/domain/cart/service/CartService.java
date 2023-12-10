package com.side.bmarket.domain.cart.service;

import com.side.bmarket.domain.cart.dto.response.CartListResponseDto;
import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.cart.exception.NotFoundCartItemException;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.cart.repository.CartRepository;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.exception.NotFoundProductException;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.exception.NotFoundUserException;
import com.side.bmarket.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public void saveCartItem(Long userId, Long productID, int quantity) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException("해당 유저가 없습니다."));

        Carts cart = cartRepository.findByUsersId(userId)
                .orElseGet(() -> cartRepository.save(
                        Carts.builder()
                                .users(user)
                                .build())
                );

        Products product = productRepository.findById(productID)
                .orElseThrow(() -> new NotFoundProductException("해당 상품이 없습니다"));

        Optional<CartItems> existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        existingCartItem.ifPresentOrElse(
                cartItem -> updateCartItemQuantity(cartItem.getId(), quantity),
                () -> {
                    CartItems newCartItem = CartItems.builder()
                            .cart(cart)
                            .product(product)
                            .productQuantity(quantity)
                            .build();
                    cartItemRepository.save(newCartItem);
                }
        );
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
        Carts cart = cartRepository.findByUsersId(1L)
                .orElseThrow(() -> new NotFoundUserException("해당 유저가 없습니다."));

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
