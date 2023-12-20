package com.side.bmarket.domain.order.service;


import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.order.entity.OrderItems;
import com.side.bmarket.domain.order.entity.OrderStatus;
import com.side.bmarket.domain.order.entity.Orders;
import com.side.bmarket.domain.order.repository.OrderRepository;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    // 주문 생성
    public void createOrder(List<Long> cartItemId, Long userId) {
        List<CartItems> cartItems = cartItemRepository.findByCartIdIn(cartItemId);
        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다"));

        List<OrderItems> createOrderItem = createOrderItem(cartItemId);

        // 총 가격 계산
        int totalPrice = calculateTotalPrice(cartItems);

        // 총 가격 기준으로 배달비 계산
        int deliveryFee = calculateDeliveryFee(totalPrice);

        Orders order = Orders.builder()
                .orderItems(createOrderItem)
                .orderStatus(OrderStatus.PENDING)
                .deliveryFee(deliveryFee)
                .build();

        orderRepository.save(order);

    }

    // 주문 아이템 생성
    public List<OrderItems> createOrderItem(List<Long> cartItemId) {
        List<CartItems> cartItems = cartItemRepository.findByCartIdIn(cartItemId);

        // 해당 상품의 재고가 충분한지 검증
        return cartItems.stream()
                .map(i -> OrderItems.builder()
                        .product(i.getProduct())
                        .quantity(i.getProductQuantity())
                        .build()
                ).collect(Collectors.toList());
    }

    // 주문 삭제
    public void deleteOrder() {
    }

    // 주문 상태 업데이트
    public void updateOrderStatus() {
    }

    // 총 금액
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

    // 재고 수량 확인 (검증)
    private boolean verifyProductQunatity(Long productId, int quantity) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(""));

        if (product.getQuantity() < quantity) return false;
        else return true;

    }

}
