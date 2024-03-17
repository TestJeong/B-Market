package com.side.bmarket.domain.order.dto.response;

import com.side.bmarket.domain.order.entity.OrderStatus;
import com.side.bmarket.domain.order.entity.Orders;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class OrderResponseDto {
    private final Long orderId;
    private final String name;
    private final int totalPrice;
    private final LocalDateTime date;
    private final OrderStatus orderStatus;

    @Builder
    public OrderResponseDto(Orders orders) {
        this.orderId = orders.getId();
        this.name = orders.getOrderName();
        this.totalPrice = orders.getOrderPrice();
        this.date = orders.getCreateDateTime();
        this.orderStatus = orders.getOrderStatus();
    }
}
