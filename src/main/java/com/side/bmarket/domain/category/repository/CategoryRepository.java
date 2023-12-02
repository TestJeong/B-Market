package com.side.bmarket.domain.category.repository;

import com.side.bmarket.domain.category.entity.Categorys;
import com.side.bmarket.domain.category.entity.SubCategorys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public List<Categorys> findAllCategory() {
        return em.createQuery(
                        "select c from Categorys c", Categorys.class
                )
                .getResultList();
    }

    public List<SubCategorys> findSubCategoryByCategory(Long categoryId) {
        return em.createQuery(
                        "select s from SubCategorys s where s.category.id =:categoryId", SubCategorys.class
                )
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    public SubCategorys findBySubCategorys(Long subCategoryID) {
        return em.find(SubCategorys.class, subCategoryID);
    }
}
