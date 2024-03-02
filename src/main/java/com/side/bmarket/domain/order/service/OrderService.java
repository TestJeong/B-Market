package com.side.bmarket.domain.order.service;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.order.dto.response.OrderHistoryListDto;
import com.side.bmarket.domain.order.dto.response.OrderResponseDto;
import com.side.bmarket.domain.order.entity.OrderItems;
import com.side.bmarket.domain.order.entity.OrderStatus;
import com.side.bmarket.domain.order.entity.Orders;
import com.side.bmarket.domain.order.exception.OutOfStockProductItemException;
import com.side.bmarket.domain.order.repository.OrderRepository;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.exception.NotFoundUserException;
import com.side.bmarket.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    // 주문 생성
    @Transactional
    public void createOrder(List<Long> cartItemId, Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException("유저 정보가 없습니다"));

        List<OrderItems> createOrderItem = createOrderItem(cartItemId);
        Orders order = Orders.builder()
                .user(user)
                .orderItems(createOrderItem)
                .orderStatus(OrderStatus.PENDING)
                .build();

        orderRepository.save(order);
    }

    // 주문 아이템 생성
    @Transactional
    public List<OrderItems> createOrderItem(List<Long> cartItemId) {
        List<CartItems> cartItems = cartItemRepository.findByIdIn(cartItemId);

        return cartItems.stream()
                .map(i -> {
                            verifyProductQuantity(i.getProduct(), i.getProductQuantity());
                            i.getProduct().decreaseQuantity(i.getProductQuantity());
                            return OrderItems.builder()
                                    .product(i.getProduct())
                                    .quantity(i.getProductQuantity())
                                    .build();
                        }
                ).collect(Collectors.toList());
    }

    // 주문 내역
    @Transactional(readOnly = true)
    public OrderHistoryListDto findOrderByUser(Long userId, int currentPage) {
        Slice<Orders> orderList = orderRepository.findByUserId(userId, PageRequest.of(currentPage, 10));

        List<OrderResponseDto> orderLists = orderList.stream()
                .map((i) -> OrderResponseDto.builder()
                        .orderId(i.getId())
                        .name(i.getOrderName())
                        .totalPrice(i.getOrderPrice())
                        .orderStatus(i.getOrderStatus())
                        .build())
                .collect(Collectors.toList());


        return OrderHistoryListDto.builder()
                .currentPage(currentPage)
                .hasNextPage(orderList.hasNext())
                .item(orderLists)
                .build();
    }

    // 주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow();

        order.updateOrderStatus(OrderStatus.CANCELED);
        order.getOrderItems().forEach(
                orderItems -> orderItems.getProduct().increaseQuantity(orderItems.getQuantity())
        );
    }

    // 주문 가능 수량 확인
    private void verifyProductQuantity(Products product, int quantity) {
        if (product.getQuantity() < quantity) throw new OutOfStockProductItemException("재고 수량이 부족합니다.");
    }
}
