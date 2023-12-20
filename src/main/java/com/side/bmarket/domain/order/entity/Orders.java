package com.side.bmarket.domain.order.entity;

import com.side.bmarket.domain.user.entity.Users;
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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems = new ArrayList<>();

    @Column(name = "delivery_fee")
    private int deliveryFee;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Builder
    public Orders(Users user, List<OrderItems> orderItems, int deliveryFee, OrderStatus orderStatus) {
        this.user = user;
        this.orderItems = orderItems;
        this.deliveryFee = deliveryFee;
        this.orderStatus = orderStatus;
    }
}
