package com.side.bmarket.domain.cart.entity;

import com.side.bmarket.common.BaseTimeEntity;
import com.side.bmarket.domain.prodcut.entity.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CartItems extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public CartItems(final Carts cart, final Products product, final int productQuantity) {
        this.cart = cart;
        this.product = product;
        this.productQuantity = productQuantity;
    }

    public void updateQuantity(final int quantity) {
        this.productQuantity += quantity;
    }
}
