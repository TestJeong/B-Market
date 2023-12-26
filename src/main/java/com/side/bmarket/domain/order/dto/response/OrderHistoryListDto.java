package com.side.bmarket.domain.order.dto.response;

import com.side.bmarket.domain.order.entity.OrderStatus;
import lombok.Getter;

import java.util.Date;

@Getter
public class OrderHistoryListDto {
    private Long orderId;
    private String name;
    private int totalPrice;
    private Date date;
    private OrderStatus orderStatus;
}
