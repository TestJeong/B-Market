package com.side.bmarket.domain.order.entity;

import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.user.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products product;

    private int quantity;

    private int price;

    @Builder
    public OrderItems(Orders order, Products product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = calculatePrice();
    }

    public int calculatePrice () {
       return this.price = (product.getProductPrice() - product.getDiscountPrice()) * quantity;
    }
}
