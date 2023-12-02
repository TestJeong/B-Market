package com.side.bmarket.domain.cart;

import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CartItems {
    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Carts cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products product;

    @Column(name = "product_quantity")
    private int productQuantity;
}
