package com.side.bmarket.domain.category.api;

import com.side.bmarket.common.dto.ErrorDto;
import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.domain.category.dto.response.CategoryDto;
import com.side.bmarket.domain.category.dto.response.SubCategoryDto;
import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Category", description = "Category & SubCategory 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/category")
    @Operation(summary = "카테고리 조회", description = "카테고리 리스트를 조회합니다.")
    public ResponseEntityDto<List<CategoryDto>> getCategoryList() {
        return ResponseEntityDto.of(HttpStatus.OK, categoryService.findAllCategory());
    }

    @GetMapping("/category/subcategory/{categoryId}")
    @Operation(summary = "서브 카테고리 조회", description = "서브 카테고리 리스트를 조회합니다.")
    @Parameter(name = "categoryId", description = "카테고리 ID", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(
                    responseCode = "401", description = "유효하지 않는 상품 카테고리 ID",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    public ResponseEntityDto<List<SubCategoryDto>> getSubCategoryList(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntityDto.of(HttpStatus.OK, categoryService.findBySubCategory(categoryId));
    }

}
