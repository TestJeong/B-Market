package com.side.bmarket.domain.order.service;


import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
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
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    // 주문 생성
    public void createOrder(List<Long> cartItemId, Long userId) {
        List<CartItems> cartItems = cartItemRepository.findByCartIdIn(cartItemId);
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다"));

        List<OrderItems> createOrderItem = createOrderItem(cartItemId);

        int totalPrice = calculateTotalPrice(cartItems);
        int deliveryFee = calculateDeliveryFee(totalPrice);

        Orders order = Orders.builder()
                .user(user)
                .orderItems(createOrderItem)
                .orderStatus(OrderStatus.PENDING)
                .deliveryFee(deliveryFee)
                .build();

        orderRepository.save(order);

    }

    // 주문 아이템 생성
    public List<OrderItems> createOrderItem(List<Long> cartItemId) {
        List<CartItems> cartItems = cartItemRepository.findByCartIdIn(cartItemId);

        return cartItems.stream()
                .map(i -> {
                            verifyProductQunatity(i.getProduct(), i.getProductQuantity());
                            return OrderItems.builder()
                                    .product(i.getProduct())
                                    .quantity(i.getProductQuantity())
                                    .build();
                        }
                ).collect(Collectors.toList());
    }

    // 주문 삭제
    public void deleteOrder() {
    }

    // 주문 상태 업데이트
    public void updateOrderStatus() {
    }

    // 총 금액 계산
    public int calculateTotalPrice(List<CartItems> cartItems) {
        return cartItems.stream()
                .mapToInt(i -> (i.getProduct().getProductPrice() - i.getProduct().getDiscountPrice()) * i.getProductQuantity())
                .sum();
    }

    // 배달비 계산
    public int calculateDeliveryFee(int totalPrice) {
        int minimumPrice = 15000;

        if (totalPrice > minimumPrice) return 0;
        else return 3000;
    }

    // 주문 가능 수량 확인
    private void verifyProductQunatity(Products product, int quantity) {
        if (product.getQuantity() < quantity) throw new RuntimeException("재고 수량이 부족합니다.");
    }
}
