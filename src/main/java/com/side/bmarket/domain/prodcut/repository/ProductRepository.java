package com.side.bmarket.domain.prodcut.repository;

import com.side.bmarket.domain.prodcut.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    Page<Products> findBySubCategoryId(Long id, Pageable pageable);
}
