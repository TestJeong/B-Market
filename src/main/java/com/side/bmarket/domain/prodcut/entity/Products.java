package com.side.bmarket.domain.prodcut.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.side.bmarket.common.BaseTimeEntity;
import com.side.bmarket.domain.category.entity.SubCategorys;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Products extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "discount_rate")
    private int discountRate;

    @Column(name = "quantity")
    private int quantity;

    @Builder
    public Products(SubCategorys subCategory, String productName, String productDescription, int productPrice, int discountPrice, int discountRate, int quantity) {
        this.subCategory = subCategory;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        this.discountRate = discountRate;
        this.quantity = quantity;
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
}
