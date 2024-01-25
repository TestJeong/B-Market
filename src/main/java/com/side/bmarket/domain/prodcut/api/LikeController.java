package com.side.bmarket.domain.prodcut.api;

import com.side.bmarket.common.config.SecurityUtil;
import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.prodcut.dto.response.ProductDto;
import com.side.bmarket.domain.prodcut.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    //    찜 하기
    @PostMapping("")
    public ResponseEntityDto<String> saveLike(@RequestBody Long productId) {
        likeService.createLike(SecurityUtil.getCurrentMemberId(),productId);
        return ResponseEntityDto.of(HttpStatus.OK, "찜 목록에 추가하였습니다.");
    }

    //    찜 삭제
    @PostMapping("/cancel")
    public ResponseEntityDto<String> deleteLike(@RequestBody Long productId) {
        likeService.deleteLike(SecurityUtil.getCurrentMemberId(),productId);
        return ResponseEntityDto.of(HttpStatus.OK, "찜 목록에 삭제하였습니다.");
    }

    //    찜 목록 조회
    @GetMapping("/list")
    public ResponseEntityDto<List<ProductDto>> getLikeList() {
        List<ProductDto> productDtoList = likeService.findLikeByUser(SecurityUtil.getCurrentMemberId());
        return ResponseEntityDto.of(HttpStatus.OK, productDtoList);
    }
}
