package com.side.bmarket.domain.order.service;


import com.side.bmarket.domain.order.repository.OrderItemRepository;
import com.side.bmarket.domain.order.repository.OrderRepository;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import com.side.bmarket.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // 주문 생성
    public void saveOrder() {

    }

    // 주문 아이템 생성
    public void saveOrderItem() {
    }

    // 주문 삭제
    public void deleteOrder() {
    }

    // 배달비 계산
    public void calculateDeliveryFee() {
    }

    // 주문 상태 업데이트
    public void updateOrderStatus() {
    }

    // 재고 수량 확인 (검증)
    private void verifyProductQunatity() {
    }

}
