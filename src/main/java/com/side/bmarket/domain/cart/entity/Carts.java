package com.side.bmarket.domain.cart.entity;


import com.side.bmarket.domain.user.entity.User;
import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Carts {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToMany(mappedBy = "cart")
    @BatchSize(size = 1000)
    private List<CartItems> cartItem = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
