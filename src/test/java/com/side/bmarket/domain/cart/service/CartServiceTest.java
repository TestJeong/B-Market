package com.side.bmarket.domain.cart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import com.side.bmarket.domain.cart.dto.response.CartListResponseDto;
import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.cart.repository.CartRepository;
import com.side.bmarket.domain.cart.support.CartFixture;
import com.side.bmarket.domain.cart.support.CartItemFixture;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import com.side.bmarket.domain.product.support.ProductFixture;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.repository.UserRepository;
import com.side.bmarket.domain.user.support.UserFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    Users user;
    Carts cart;
    CartItems cartItem;

    @InjectMocks
    CartService cartService;

    @Mock
    CartItemRepository cartItemRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        user = UserFixture.createUser("테스트1");
        cart = CartFixture.createCart(user);
    }

    @DisplayName("장바구니에 상품을 저장합니다.")
    @Test
    void saveCartItem() {
        // given
        Products product1 = ProductFixture.createProduct("상품1", 1000, 100, 0, 0);
        cartItem = CartItemFixture.createCartItem(cart, product1, 1);

        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
        given(cartRepository.findByUsersId(any())).willReturn(Optional.ofNullable(cart));
        given(productRepository.findById(any())).willReturn(Optional.ofNullable(product1));
        given(cartItemRepository.save(any())).willReturn(cartItem);

        // when
        cartService.saveCartItem(1L, 1L, 1);

        // then
        then(cartItemRepository).should().save(any());
    }

    @DisplayName("장바구니에 같은 상품이 있을경우 수량만 증가합니다.")
    @Test
    void updateCartItemQuantity() {
        // given
        Products product1 = ProductFixture.createProduct("상품1", 1000, 100, 0, 10);
        cartItem = CartItemFixture.createCartItem(cart, product1, 1);

        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
        given(cartRepository.findByUsersId(any())).willReturn(Optional.ofNullable(cart));
        given(productRepository.findById(any())).willReturn(Optional.ofNullable(product1));
        given(cartItemRepository.findByCartIdAndProductId(any(), any())).willReturn(Optional.ofNullable(cartItem));

        // when
        cartService.saveCartItem(1L, 1L, 2);

        // then
        assertThat(cartItem.getProductQuantity()).isEqualTo(3);
    }

    @DisplayName("장바구니에 담긴 상품의 총 가격을 계산합니다.")
    @Test
    void calculateTotalPrice() {
        // given
        Products product1 = ProductFixture.createProduct("상품2", 1000, 100, 0, 0);
        Products product2 = ProductFixture.createProduct("상품3", 1000, 100, 0, 0);
        Products product3 = ProductFixture.createProduct("상품4", 1000, 100, 0, 0);

        CartItems cartItem1 = CartItemFixture.createCartItem(cart, product1, 1);
        CartItems cartItem2 = CartItemFixture.createCartItem(cart, product2, 1);
        CartItems cartItem3 = CartItemFixture.createCartItem(cart, product3, 1);

        List<CartItems> cartItemsList = List.of(cartItem1, cartItem2, cartItem3);
        given(cartRepository.findByUsersId(any())).willReturn(Optional.ofNullable(cart));
        given(cartItemRepository.findByCartId(any())).willReturn(cartItemsList);

        // when
        CartListResponseDto totalPrice = cartService.findCartItemByUser(user.getId());

        // then
        assertThat(totalPrice.getTotalPrice()).isEqualTo(2700);
    }


}