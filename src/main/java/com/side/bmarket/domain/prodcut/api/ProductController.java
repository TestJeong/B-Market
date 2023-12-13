package com.side.bmarket.domain.prodcut.api;

import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.prodcut.dto.SortType;
import com.side.bmarket.domain.prodcut.dto.response.ProductDto;
import com.side.bmarket.domain.prodcut.dto.response.ProductListDto;
import com.side.bmarket.domain.prodcut.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    //    상품 리스트 조회
    @GetMapping("/list")
    public ResponseEntityDto<ProductListDto> getProduct(@RequestParam("subCategoryId") Long subCategoryId,
                                                        @RequestParam("currentPage") int currentPage) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductBySubcCategory(subCategoryId, currentPage));
    }

    //    상품 상세 조회
    @GetMapping("/detail")
    public ResponseEntityDto<ProductDto> getProductDetail(@RequestParam("productId") Long productId) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductDetail(productId));
    }

    //    상품 정렬
    @GetMapping("/sort")
    public ResponseEntityDto<ProductListDto> getProductSortList(@RequestParam("sortType") SortType sortType,
                                                                @RequestParam("currentPage") int currentPage) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductSort(sortType, currentPage));
    }

    //    인기 상품 리스트
    @GetMapping("/popular-list")
    public void getPopularProductList() {
    }
}
