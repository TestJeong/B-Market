package com.side.bmarket.domain.prodcut.api;

import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.prodcut.dto.SortType;
import com.side.bmarket.domain.prodcut.dto.response.ProductDto;
import com.side.bmarket.domain.prodcut.dto.response.ProductListDto;
import com.side.bmarket.domain.prodcut.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Product 관련 API")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    @Operation(summary = "상품리스트 조회", description = "상품들을 조회합니다.")
    @Parameter(name = "subCategoryId", description = "세부카테고리 Id", required = true)
    @Parameter(name = "sortType", description = "정렬 타입", required = true)
    @Parameter(name = "currentPage", description = "현재 페이지", required = true)
    public ResponseEntityDto<ProductListDto> getProduct(@RequestParam("subCategoryId") Long subCategoryId,
                                                        @RequestParam(value = "sortType", required = false, defaultValue = "DEFAULT") SortType sortType,
                                                        @RequestParam("currentPage") int currentPage) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductBySubCategory(subCategoryId, sortType, currentPage));
    }

    @GetMapping("/detail/{productId}")
    @Operation(summary = "상품 상세 조회", description = "상세 상품을 조회합니다.")
    @Parameter(name = "productId", description = "상품 Id", required = true)
    public ResponseEntityDto<ProductDto> getProductDetail(@PathVariable("productId") Long productId) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductDetail(productId));
    }


    @GetMapping("/popular-list")
    @Operation(summary = "인기상품 조회", description = "인기상품들을 조회합니다.")
    @Parameter(name = "currentPage", description = "현재 페이지", required = true)
    public ResponseEntityDto<ProductListDto> getPopularProductList(@RequestParam("currentPage") int currentPage) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductPopularList(currentPage));
    }

    @GetMapping("/max-discount")
    @Operation(summary = "최대 할인 상품 조회", description = "최대 할인 상품들을 조회합니다.")
    @Parameter(name = "currentPage", description = "현재 페이지", required = true)
    public ResponseEntityDto<ProductListDto> getMaxDiscountList(@RequestParam("currentPage") int currentPage) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProductMaxDiscount(currentPage));
    }

    @GetMapping("/search")
    @Operation(summary = "상품 검색", description = "해당 상품을 검색합니다")
    @Parameter(name = "searchTitle", description = "검색할 상품명", required = true)
    public ResponseEntityDto<List<ProductDto>> getSearchProductList(@RequestParam("searchTitle") String searchTitle) {
        return ResponseEntityDto.of(HttpStatus.OK, productService.findProduct(searchTitle));
    }

}
