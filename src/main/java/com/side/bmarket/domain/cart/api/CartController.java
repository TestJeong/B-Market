package com.side.bmarket.domain.cart.api;

import com.side.bmarket.common.config.SecurityUtil;
import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.cart.dto.request.SaveCartRequestDto;
import com.side.bmarket.domain.cart.dto.request.UpdateCartItemRequestDto;
import com.side.bmarket.domain.cart.dto.response.CartListResponseDto;
import com.side.bmarket.domain.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart", description = "Cart 관련 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/my-cart")
    @Operation(summary = "장바구니 조회", description = "나의 장바구니 담겨있는 상품들을 조회합니다.")
    public ResponseEntityDto<CartListResponseDto> getCartList() {
        return ResponseEntityDto.of(HttpStatus.OK, cartService.findCartItemByUser(SecurityUtil.getCurrentMemberId()));
    }

    @PostMapping("/cart-item")
    @Operation(summary = "장바구니 아이템 추가", description = "장바구니에 아이템을 추가합니다")
    public ResponseEntityDto<String> addCartItem(@RequestBody SaveCartRequestDto requestDTO) {
        cartService.saveCartItem(requestDTO.getProductId(), SecurityUtil.getCurrentMemberId(), requestDTO.getQuantity());
        return ResponseEntityDto.of(HttpStatus.OK, "장바구니에 상품을 추가하였습니다");
    }

    @DeleteMapping("/cart-item/{cartItemId}")
    @Operation(summary = "장바구니 아이템 삭제", description = "장바구니에 아이템을 삭제합니다")
    @Parameter(name = "cartItemId", description = "장바구니 상품 ID", required = true)
    public ResponseEntityDto<String> deleteCartItem(@PathVariable("cartItemId") Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntityDto.of(HttpStatus.OK, "장바구니에서 상품을 삭제하였습니다");
    }

//    @PatchMapping("/cart-item")
//    @Operation(summary = "장바구니 상품 수량 업데이트", description = "장바구니에 상품 수량을 업데이트 합니다")
//    public ResponseEntityDto<String> updateQuantity(@RequestBody UpdateCartItemRequestDto params) {
//        cartService.updateCartItemQuantity(params.getCartItemId(), params.getQuantity());
//        return ResponseEntityDto.of(HttpStatus.OK, "장바구니에서 해당 상품 수량을 업데이트하였습니다.");
//
//    }
}
