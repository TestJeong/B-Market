package com.side.bmarket.domain.prodcut.repository;

import com.side.bmarket.domain.prodcut.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    Slice<Products> findBySubCategoryId(Long id, Pageable pageable);

    Slice<Products> findAllByOrderByQuantityAsc(Pageable pageable);

    Slice<Products> findAllByOrderByDiscountRateDesc(Pageable pageable);

    @Query(value = "select p from Products p where p.productName like %:productName%")
    List<Products> findProduct(@Param("productName") String productName);
}
