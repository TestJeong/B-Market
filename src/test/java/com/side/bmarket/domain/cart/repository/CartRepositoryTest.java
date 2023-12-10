package com.side.bmarket.domain.cart.repository;

import com.side.bmarket.domain.cart.entity.Carts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
class CartRepositoryTest {
    @Autowired
    private CartRepository cartRepository;

    @DisplayName("테스트")
    @Test
    void test() {
        // given
        List<Carts> byUserId = cartRepository.findAll();
        // when
        System.out.println(byUserId.size());
        // then
    }

}