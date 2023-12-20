package com.side.bmarket.domain.cart.repository;

import com.side.bmarket.domain.cart.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findByCartId(Long id);

    List<CartItems> findByCartIdIn(List<Long> cartId);

    Optional<CartItems> findById(Long id);

    Optional<CartItems> findByCartIdAndProductId(Long cartId, Long productId);

}
