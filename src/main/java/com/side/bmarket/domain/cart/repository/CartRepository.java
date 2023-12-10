package com.side.bmarket.domain.cart.repository;

import com.side.bmarket.domain.cart.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Carts, Long> {
    Optional<Carts> findByUsersId(Long userId);
}
