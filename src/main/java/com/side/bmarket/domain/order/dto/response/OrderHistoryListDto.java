package com.side.bmarket.domain.order.dto.response;

import com.side.bmarket.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class OrderHistoryListDto {
    private final Long orderId;
    private final String name;
    private final int totalPrice;
    private final Date date;
    private final OrderStatus orderStatus;

    @Builder
    public OrderHistoryListDto(Long orderId, String name, int totalPrice, Date date, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.name = name;
        this.totalPrice = totalPrice;
        this.date = date;
        this.orderStatus = orderStatus;
    }
}
