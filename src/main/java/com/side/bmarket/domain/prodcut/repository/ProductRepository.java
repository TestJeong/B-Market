package com.side.bmarket.domain.prodcut.repository;

import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    //    서브 카테고리 id 기준에 맞는 상품들 가져오기
    public List<Products> findByProduct(Long subCategoryID) {
        return em.createQuery(
                        "select p from Products p where p.subCategory.id = :subCategoryID", Products.class
                ).setParameter("subCategoryID", subCategoryID)
                .getResultList();
    }

    //    상품 세부 정보
    public Products findOneProduct(Long productID) {
        return em.find(Products.class, productID);
    }

//    아이템 정렬
}
