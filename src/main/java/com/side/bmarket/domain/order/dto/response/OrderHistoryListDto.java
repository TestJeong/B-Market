package com.side.bmarket.domain.order.dto.response;

import com.side.bmarket.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class OrderHistoryListDto {

    private final int currentPage;
    private final Boolean hasNextPage;
    private final List<OrderResponseDto> item;

    @Builder
    public OrderHistoryListDto(int currentPage, Boolean hasNextPage, List<OrderResponseDto> item) {
        this.currentPage = currentPage;
        this.hasNextPage = hasNextPage;
        this.item = item;
    }
}
