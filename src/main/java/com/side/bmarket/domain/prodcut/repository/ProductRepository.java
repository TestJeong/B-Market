package com.side.bmarket.domain.prodcut.repository;

import com.side.bmarket.domain.prodcut.entity.Products;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {
    Slice<Products> findBySubCategoryId(Long id, Pageable pageable);

    Slice<Products> findAllByOrderByQuantityAsc(Pageable pageable);

    Slice<Products> findAllByOrderByDiscountRateDesc(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Products p  where p.id = :productId")
    Optional<Products> findByProductId(@Param("productId") Long productId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Products p set p.quantity = p.quantity + :quantity where p.id = :productId")
    void increaseQuantity(@Param("productId") Long productId, @Param("quantity") int quantity);

    @Query(value = "SELECT * FROM products p WHERE MATCH(p.product_name) AGAINST (CONCAT(:productName, '*') IN BOOLEAN MODE)", nativeQuery = true)
    List<Products> findProduct(@Param("productName") String productName);
}
