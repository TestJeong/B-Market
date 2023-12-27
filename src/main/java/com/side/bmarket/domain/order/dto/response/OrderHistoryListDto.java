package com.side.bmarket.domain.order.dto.response;

import com.side.bmarket.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class OrderHistoryListDto {
    private Long orderId;
    private String name;
    private int totalPrice;
    private Date date;
    private OrderStatus orderStatus;

    @Builder
    public OrderHistoryListDto(Long orderId, String name, int totalPrice, Date date, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.name = name;
        this.totalPrice = totalPrice;
        this.date = date;
        this.orderStatus = orderStatus;
    }
}
