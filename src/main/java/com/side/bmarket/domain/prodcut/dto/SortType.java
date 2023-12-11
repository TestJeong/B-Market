package com.side.bmarket.domain.prodcut.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {

    LOW_PRICE("LOW_PRICE"),
    HIGH_PRICE("HIGH_PRICE"),
    HIGHT_DISCOUNT("HIGHT_DISCOUNT");

    private final String name;

}
