package com.side.bmarket.domain.category.entity;

import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class SubCategorys {
    @Id
    @GeneratedValue
    @Column(name = "sub_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categorys category;

    @OneToMany(mappedBy = "subCategory")
    @BatchSize(size = 1000)
    private List<Products> processe = new ArrayList<>();

    @Column(name = "sub_category_name")
    private String subCategoryName;

}
