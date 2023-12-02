package com.side.bmarket.domain.prodcut.entity;

import com.side.bmarket.domain.category.entity.SubCategorys;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Products {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategorys subCategory;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price")
    private int productPrice;

    @Column(name = "discount_price")
    private int discountPrice;

    @Column(name = "quantity")
    private int quantity;


}