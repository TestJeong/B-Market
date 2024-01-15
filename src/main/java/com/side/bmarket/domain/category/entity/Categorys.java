package com.side.bmarket.domain.category.entity;

import com.side.bmarket.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Categorys extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    @BatchSize(size = 1000)
    private List<SubCategorys> subCategory = new ArrayList<>();

    @Builder
    public Categorys(String categoryName) {
        this.categoryName = categoryName;
    }
}
