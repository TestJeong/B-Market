package com.side.bmarket.domain.order.support;

import com.side.bmarket.domain.order.entity.OrderItems;
import com.side.bmarket.domain.order.entity.OrderStatus;
import com.side.bmarket.domain.order.entity.Orders;
import com.side.bmarket.domain.product.support.ProductFixture;
import com.side.bmarket.domain.user.support.UserFixture;

import java.util.ArrayList;
import java.util.List;

public class OrderFixture {

    public static Orders createOrder() {
        return Orders.builder()
                .user(UserFixture.createUser("테스트 유저1"))
                .orderItems(createOrderItem())
                .orderStatus(OrderStatus.PENDING)
                .build();
    }

    public static List<OrderItems> createOrderItem() {
        List<OrderItems> orderItemsList = new ArrayList<>();

        orderItemsList.add(OrderItems.builder()
                .product(ProductFixture.createProduct("상품1", 1000, 100, 0, 10))
                .quantity(1)
                .build());

        orderItemsList.add(OrderItems.builder()
                .product(ProductFixture.createProduct("상품2", 2000, 200, 0, 20))
                .quantity(2)
                .build());


        return orderItemsList;
    }
}
