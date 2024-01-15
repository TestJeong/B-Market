package com.side.bmarket.domain.order.api;

import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.order.dto.request.CreateOrderRequestDto;
import com.side.bmarket.domain.order.dto.response.OrderHistoryListDto;
import com.side.bmarket.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    // 주문 생성
    @PostMapping("/")
    public ResponseEntityDto<String> createOrder(@RequestBody CreateOrderRequestDto requestDto) {
        orderService.createOrder(requestDto.getCartItemId());
        return ResponseEntityDto.of(HttpStatus.OK, "주문을 생성 하였습니다");
    }

    // 주문 취소
    @PostMapping("/cancel")
    public ResponseEntityDto<String> cancleOrder(@RequestBody Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntityDto.of(HttpStatus.OK, "주문을 취소 하였습니다");
    }

    // 주문 내역
    @GetMapping("/my-order")
    public List<OrderHistoryListDto> getOrderList() {
        return orderService.findOrderByUser();
    }
}
