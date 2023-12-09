package com.side.bmarket.domain.cart.repository;

import com.side.bmarket.domain.cart.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Carts, Long> {
    Carts findByUserId(Long Id);
}
