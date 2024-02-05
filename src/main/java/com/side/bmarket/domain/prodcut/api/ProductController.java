package com.side.bmarket.domain.prodcut.api;

import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.prodcut.dto.SortType;
import com.side.bmarket.domain.prodcut.dto.response.ProductDto;
import com.side.bmarket.domain.prodcut.dto.response.ProductListDto;
import com.side.bmarket.domain.prodcut.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    //    상품 리스트 조회
    @GetMapping("/list")
    public ResponseEntityDto<ProductListDto> getProduct(@RequestParam("subCategoryId") Long subCategoryId,
                                                        @RequestParam(value = "sortType", required = false, defaultValue = "DEFAULT") SortType sortType,
                                                        @RequestParam("currentPage") int currentPage) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductBySubCategory(subCategoryId, sortType, currentPage));
    }

    //    상품 상세 조회
    @GetMapping("/detail/{productId}")
    public ResponseEntityDto<ProductDto> getProductDetail(@PathVariable("productId") Long productId) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductDetail(productId));
    }


    //    인기 상품 리스트
    @GetMapping("/popular-list")
    public ResponseEntityDto<ProductListDto> getPopularProductList(@RequestParam("currentPage") int currentPage) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductPopularList(currentPage));
    }

    //    추천 상품 리스트
    @GetMapping("/recommend-list")
    public void getRecommendList() {
    }

    //    최대 할인 상품 리스트
    @GetMapping("/max-discount")
    public ResponseEntityDto<ProductListDto> getMaxDiscountList(@RequestParam("currentPage") int currentPage) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductMaxDiscount(currentPage));
    }

}
