package com.side.bmarket.domain.category.entity;

import com.side.bmarket.common.BaseTimeEntity;
import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class SubCategorys extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categorys category;

    @OneToMany(mappedBy = "subCategory")
    @BatchSize(size = 100)
    private List<Products> product = new ArrayList<>();

    @Column(name = "sub_category_name")
    private String subCategoryName;

    @Builder
    public SubCategorys(Categorys category, String subCategoryName) {
        this.category = category;
        this.subCategoryName = subCategoryName;
    }
}
