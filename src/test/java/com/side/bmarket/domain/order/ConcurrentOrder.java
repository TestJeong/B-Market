package com.side.bmarket.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import com.side.bmarket.domain.cart.repository.CartItemRepository;
import com.side.bmarket.domain.cart.repository.CartRepository;
import com.side.bmarket.domain.cart.support.CartFixture;
import com.side.bmarket.domain.cart.support.CartItemFixture;
import com.side.bmarket.domain.order.service.OrderService;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
@ActiveProfiles("test")
public class ConcurrentOrder {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderService orderService;

    @DisplayName("주문 동시성을 테스트 합니다")
    @Test
    void test() throws InterruptedException {
        // given
        Users user = userRepository.findById(1L)
                .orElseThrow();

        Products products = productRepository.findById(1L)
                .orElseThrow();

        Carts cart = CartFixture.createCart(user);
        cartRepository.save(cart);

        CartItems cartItem1 = CartItemFixture.createCartItem(cart, products, 1);
        cartItemRepository.save(cartItem1);

        List<Long> cartItemIdList = Arrays.asList(1L);

        final int threadCount = 50;
        final int numberOfThreads = 32;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Long userId = user.getId();

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    orderService.createOrder(cartItemIdList, userId);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // then
        Products updateProduct = productRepository.findById(1L).orElseThrow();
        assertThat(updateProduct.getQuantity()).isEqualTo(50);
    }
}