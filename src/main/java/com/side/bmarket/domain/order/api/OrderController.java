package com.side.bmarket.domain.order.api;

import com.side.bmarket.common.config.SecurityUtil;
import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.order.dto.request.CreateOrderRequestDto;
import com.side.bmarket.domain.order.dto.response.OrderHistoryListDto;
import com.side.bmarket.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Tag(name = "Order", description = "Order 관련 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    // 주문 생성
    @PostMapping("/order")
    @Operation(summary = "주문 생성", description = "주문을 생성합니다.")
    public ResponseEntityDto<String> createOrder(@Valid @RequestBody CreateOrderRequestDto requestDto) {
        orderService.createOrder(requestDto.getCartItemId(), SecurityUtil.getCurrentMemberId());
        return ResponseEntityDto.of(HttpStatus.OK, "주문을 생성 하였습니다");
    }

    // 주문 취소
    @DeleteMapping("/order/cancel/{orderId}")
    @Operation(summary = "주문 취소", description = "주문을 삭제 합니다")
    @Parameter(name = "orderId", description = "주문 Id", required = true)
    public ResponseEntityDto<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntityDto.of(HttpStatus.OK, "주문을 취소 하였습니다");
    }

    // 주문 내역
    @GetMapping("/order/my-order")
    @Operation(summary = "내 주문내역 조회", description = "나의 주문내역을 조회합니다.")
    public ResponseEntityDto<OrderHistoryListDto> getOrderList(@RequestParam("currentPage") int currentPage) {
        return ResponseEntityDto.of(HttpStatus.OK, orderService.findOrderByUser(SecurityUtil.getCurrentMemberId(), currentPage));
    }
}

