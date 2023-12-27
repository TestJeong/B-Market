package com.side.bmarket.domain.order.service;


import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.order.dto.response.OrderHistoryListDto;
import com.side.bmarket.domain.order.entity.OrderItems;
import com.side.bmarket.domain.order.entity.OrderStatus;
import com.side.bmarket.domain.order.entity.Orders;
import com.side.bmarket.domain.order.repository.OrderRepository;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    // 주문 생성
    @Transactional
    public void createOrder(List<Long> cartItemId, Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다"));

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
        List<CartItems> cartItems = cartItemRepository.findByCartIdIn(cartItemId);

        return cartItems.stream()
                .map(i -> {
                            verifyProductQunatity(i.getProduct(), i.getProductQuantity());
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
    public List<OrderHistoryListDto> findOrderByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map((i) -> OrderHistoryListDto.builder()
                        .orderId(i.getId())
                        .name(i.getOrderName())
                        .totalPrice(i.getOrderPrice())
                        .orderStatus(i.getOrderStatus())
                        .build())
                .collect(Collectors.toList());
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
    private void verifyProductQunatity(Products product, int quantity) {
        if (product.getQuantity() < quantity) throw new RuntimeException("재고 수량이 부족합니다.");
    }
}
