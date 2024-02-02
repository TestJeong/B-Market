package com.side.bmarket.domain.category.repository;

import com.side.bmarket.domain.category.entity.Categorys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categorys, Long> {
}
