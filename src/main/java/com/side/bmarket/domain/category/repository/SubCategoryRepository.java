package com.side.bmarket.domain.category.repository;

import com.side.bmarket.domain.category.entity.SubCategorys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategorys, Long> {
    List<SubCategorys> findByCategoryId(Long categoryId);
}
