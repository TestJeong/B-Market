package com.side.bmarket.domain.order.entity;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING,
    PAYED,
    DELIVERING,
    COMPLETED,
    CANCELED,
}
