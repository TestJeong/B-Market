package com.side.bmarket.domain.order.service;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.cart.support.CartFixture;
import com.side.bmarket.domain.cart.support.CartItemFixture;
import com.side.bmarket.domain.order.dto.response.OrderHistoryListDto;
import com.side.bmarket.domain.order.entity.OrderItems;
import com.side.bmarket.domain.order.entity.Orders;
import com.side.bmarket.domain.order.repository.OrderRepository;
import com.side.bmarket.domain.order.support.OrderFixture;
import com.side.bmarket.domain.prodcut.entity.Products;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    Users user;
    Products product;
    Carts cart;
    CartItems cartItem;

    @InjectMocks
    OrderService orderService;

    @Mock
    CartItemRepository cartItemRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        user = UserFixture.createUser("테스트 유저1");
        cart = CartFixture.createCart(user);
        cartItem = CartItemFixture.createCartItem(cart, product, 1);
    }

    @DisplayName("주문을 생성합니다.")
    @Test
    void createOrder() {
        // given
        Products product1 = ProductFixture.createProduct("상품2", 1000, 100, 0, 10);
        Products product2 = ProductFixture.createProduct("상품3", 1000, 100, 0, 10);
        Products product3 = ProductFixture.createProduct("상품4", 1000, 100, 0, 10);

        CartItems cartItem1 = CartItemFixture.createCartItem(cart, product1, 1);
        CartItems cartItem2 = CartItemFixture.createCartItem(cart, product2, 1);
        CartItems cartItem3 = CartItemFixture.createCartItem(cart, product3, 1);

        List<CartItems> cartItemsList = List.of(cartItem1, cartItem2, cartItem3);

        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
        given(cartItemRepository.findByIdIn(any())).willReturn(cartItemsList);

        // when
        List<Long> cartItemIdList = Arrays.asList(1L, 2L, 3L);
        orderService.createOrder(cartItemIdList, 1L);

        // then
        then(orderRepository).should().save(any());
    }


    @DisplayName("주문 아이템을 생성합니다.")
    @Test
    void createOrderItem() {
        // given
        Products product1 = ProductFixture.createProduct("상품2", 1000, 100, 0, 10);
        Products product2 = ProductFixture.createProduct("상품3", 1000, 100, 0, 10);
        Products product3 = ProductFixture.createProduct("상품4", 1000, 100, 0, 10);

        CartItems cartItem1 = CartItemFixture.createCartItem(cart, product1, 1);
        CartItems cartItem2 = CartItemFixture.createCartItem(cart, product2, 1);
        CartItems cartItem3 = CartItemFixture.createCartItem(cart, product3, 1);

        List<CartItems> cartItemsList = List.of(cartItem1, cartItem2, cartItem3);

        given(cartItemRepository.findByIdIn(any())).willReturn(cartItemsList);

        // when
        List<Long> cartItemIdList = Arrays.asList(1L, 2L, 3L);
        List<OrderItems> result = orderService.createOrderItem(cartItemIdList);

        // then
        assertThat(result.size()).isEqualTo(3);
    }

    @DisplayName("주문 아이템을 생성 후 상품 갯수를 차감합니다.")
    @Test
    void decreaseQuantity() {
        // given
        Products product1 = ProductFixture.createProduct("상품2", 1000, 100, 0, 10);

        CartItems cartItem1 = CartItemFixture.createCartItem(cart, product1, 2);

        List<CartItems> cartItemsList = List.of(cartItem1);

        given(cartItemRepository.findByIdIn(any())).willReturn(cartItemsList);

        // when
        List<Long> cartItemIdList = Arrays.asList(1L);
        orderService.createOrderItem(cartItemIdList);

        // then
        assertThat(product1.getQuantity()).isEqualTo(8);
    }

    @DisplayName("주문 아이템 생성중 상품 재고 수량이 부족하면 예외가 발생됩니다.")
    @Test
    void createOrderItemException() {
        // given
        Products product1 = ProductFixture.createProduct("상품1", 1000, 100, 0, 5);
        CartItems cartItem1 = CartItemFixture.createCartItem(cart, product1, 6);
        List<CartItems> cartItemsList = List.of(cartItem1);

        given(cartItemRepository.findByIdIn(any())).willReturn(cartItemsList);

        // when
        List<Long> cartItemIdList = List.of(1L);

        // then
        assertThatThrownBy(() ->
                orderService.createOrderItem(cartItemIdList)
        )
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("재고 수량이 부족합니다.");
    }

    @DisplayName("유저 아이디 기반으로 주문 내역을 불러옵니다.")
    @Test
    void findOrderByUser() {
        // given
       Orders order = OrderFixture.createOrder();
       given(orderRepository.findByUserId(any())).willReturn(Collections.singletonList(order));

        // when
        List<OrderHistoryListDto> orderHistoryList = orderService.findOrderByUser(1L);

        // then
        assertThat(orderHistoryList.get(0).getName()).isEqualTo("상품1 외 1개");
        assertThat(orderHistoryList.get(0).getTotalPrice()).isEqualTo(4500);
    }

}