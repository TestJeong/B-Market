package com.side.bmarket.domain.order.entity;

import com.side.bmarket.common.BaseTimeEntity;
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
public class Orders extends BaseTimeEntity {
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

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_price")
    private int orderPrice;

    @Builder
    public Orders(Users user, List<OrderItems> orderItems, OrderStatus orderStatus) {
        this.user = user;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        createOrderName();
        calculateTotalPrice();
    }

    public void updateOrderStatus(OrderStatus status) {
        this.orderStatus = status;
    }

    public void createOrderName() {
        String name = "";

        if (orderItems.size() == 1) name = orderItems.get(0).getProduct().getProductName();
        else name = orderItems.get(0).getProduct().getProductName() + " 외 " + (orderItems.size() - 1) + "개";

        this.orderName = name;
    }

    public void calculateTotalPrice() {
        int totalPrice = 0;

        for (OrderItems orderItem : orderItems) {
            totalPrice += orderItem.calculatePrice();
        }
        this.orderPrice = totalPrice;
        this.deliveryFee = calculateDeliveryFee(totalPrice);

    }

    public int calculateDeliveryFee(int totalPrice) {
        int minimumPrice = 15000;

        if (totalPrice > minimumPrice) return 0;
        else return 3000;
    }
}
