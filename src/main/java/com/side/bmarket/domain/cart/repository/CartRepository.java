package com.side.bmarket.domain.cart.repository;

import com.side.bmarket.domain.cart.entity.CartItems;
import com.side.bmarket.domain.cart.entity.Carts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {
    private final EntityManager em;

    public void saveCartItem(CartItems cartItem) {
        em.persist(cartItem);
    }

    public Carts findCartByUser(Long userID) {
        return em.find(Carts.class, userID);
    }

    public CartItems findByCartItem(Long cartItemID) {
        return em.find(CartItems.class, cartItemID);
    }

    public List<CartItems> findCartItemByCart(Long cartID) {
        return em.createQuery("select ci from CartItems ci where ci.cart.id = :cartID",
                        CartItems.class)
                .setParameter("cartID", cartID)
                .getResultList();
    }
}