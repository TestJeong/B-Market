package com.side.bmarket.domain.order.service;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.exception.NotFoundCartItemException;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.order.dto.response.OrderHistoryListDto;
import com.side.bmarket.domain.order.dto.response.OrderResponseDto;
import com.side.bmarket.domain.order.entity.OrderItems;
import com.side.bmarket.domain.order.entity.OrderStatus;
import com.side.bmarket.domain.order.entity.Orders;
import com.side.bmarket.domain.order.exception.NotFoundOrderException;
import com.side.bmarket.domain.order.exception.OutOfStockProductItemException;
import com.side.bmarket.domain.order.repository.OrderRepository;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.exception.NotFoundProductException;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // 주문 생성
    @Transactional
    public void createOrder(List<Long> cartItemId, Long userId) {
        Users user = findUserById(userId);

        List<OrderItems> orderItems = cartItemId.stream()
                .map(this::createOrderItem)
                .collect(Collectors.toList());

        Orders order = Orders.builder()
                .user(user)
                .orderItems(orderItems)
                .orderStatus(OrderStatus.PENDING)
                .build();

        orderRepository.save(order);
    }

    // 주문 아이템 생성
    private OrderItems createOrderItem(Long cartItemId) {
        CartItems cartItem = findCartItemById(cartItemId);
        verifyProductQuantity(cartItem.getProduct(), cartItem.getProductQuantity());
        cartItem.getProduct().decreaseQuantity(cartItem.getProductQuantity());

        return OrderItems.builder()
                .product(cartItem.getProduct())
                .quantity(cartItem.getProductQuantity())
                .build();
    }

    // 주문 내역
    @Transactional(readOnly = true)
    public OrderHistoryListDto findOrderByUser(Long userId, int currentPage) {
        Slice<Orders> orderList = orderRepository.findByUserId(userId, PageRequest.of(currentPage, 10));

        List<OrderResponseDto> orderLists = orderList.stream()
                .map(OrderResponseDto::new)
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
        Orders order = findOrderById(orderId);
        order.updateOrderStatus(OrderStatus.CANCELED);
        order.getOrderItems().forEach(
                orderItems -> productRepository.increaseQuantity(orderItems.getProduct().getId(), orderItems.getQuantity())
        );
    }

    // 주문 가능 수량 확인
    private void verifyProductQuantity(Products product, int quantity) {
        Products products = findProductById(product.getId());
        if (products.getQuantity() < quantity) throw new OutOfStockProductItemException("재고 수량이 부족합니다.");
    }

    private Orders findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundOrderException("존재하지 않는 주문입니다."));
    }

    private Products findProductById(final Long productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundProductException("존재하지 않는 상품입니다."));
    }

    private Users findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException("유저 정보가 없습니다."));
    }

    private CartItems findCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new NotFoundCartItemException("해당 cartItem이 없습니다."));
    }
}
