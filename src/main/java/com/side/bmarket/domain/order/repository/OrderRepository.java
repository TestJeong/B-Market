package com.side.bmarket.domain.order.repository;

import com.side.bmarket.domain.order.entity.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Slice<Orders> findByUserId(Long userId, Pageable pageable);
}
