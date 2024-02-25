package com.side.bmarket.domain.prodcut.api;

import com.side.bmarket.common.config.SecurityUtil;
import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.prodcut.dto.response.ProductDto;
import com.side.bmarket.domain.prodcut.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Like", description = "Like 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/like")
    @Operation(summary = "찜 하기", description = "해당 상품을 찜 목록에 추가합니다")
    public ResponseEntityDto<String> saveLike(@RequestBody Long productId) {
        likeService.createLike(SecurityUtil.getCurrentMemberId(), productId);
        return ResponseEntityDto.of(HttpStatus.OK, "찜 목록에 추가하였습니다.");
    }

    @DeleteMapping("/like/cancel/{productId}")
    @Operation(summary = "찜 삭제", description = "해당 상품을 찜 목록에서 삭제합니다.")
    @Parameter(name = "productId", description = "상품 ID", required = true)
    public ResponseEntityDto<String> deleteLike(@PathVariable Long productId) {
        likeService.deleteLike(SecurityUtil.getCurrentMemberId(), productId);
        return ResponseEntityDto.of(HttpStatus.OK, "찜 목록에 삭제하였습니다.");
    }

    @GetMapping("/like/list")
    @Operation(summary = "찜 리스트 조회", description = "나의 찜 목록 리스트를 조회합니다.")
    public ResponseEntityDto<List<ProductDto>> getLikeList(@RequestParam("currentPage") int currentPage) {
        List<ProductDto> productDtoList = likeService.findLikeByUser(SecurityUtil.getCurrentMemberId(), currentPage);
        return ResponseEntityDto.of(HttpStatus.OK, productDtoList);
    }
}
