package com.side.bmarket.domain.cart.api;

import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.cart.dto.request.SaveCartRequestDto;
import com.side.bmarket.domain.cart.dto.request.UpdateCartItemRequestDto;
import com.side.bmarket.domain.cart.dto.response.CartListResponseDto;
import com.side.bmarket.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/my-cart")
    public ResponseEntity<CartListResponseDto> getCartList() {
        return ResponseEntity.ok()
                .body(cartService.findCartItemByUser());
    }

    @PostMapping("/cart-item")
    public ResponseEntity<ResponseEntityDto> addCartItem(@RequestBody SaveCartRequestDto requestDTO) {
        cartService.saveCartItem(requestDTO.getProductID(), requestDTO.getQuantity());
        return ResponseEntity.ok()
                .body(new ResponseEntityDto("장바구니에 상품을 추가하였습니다"));
    }

    @DeleteMapping("/cart-item")
    public ResponseEntity<ResponseEntityDto> deleteCartItem(@RequestBody Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.ok()
                .body(new ResponseEntityDto("장바구니에서 상품을 삭제하였습니다"));
    }

    @PatchMapping("/cart-item")
    public ResponseEntity<ResponseEntityDto> updateQuantity(@RequestBody UpdateCartItemRequestDto params) {
        cartService.updateCartItemQuantity(params.getCartItemId(), params.getQuantity());
        return ResponseEntity.ok()
                .body(new ResponseEntityDto("장바구니에서 해당 상품 수량을 업데이트하였습니다."));
    }
}
