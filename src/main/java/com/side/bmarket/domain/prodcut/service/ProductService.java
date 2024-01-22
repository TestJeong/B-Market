package com.side.bmarket.domain.prodcut.service;

import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.repository.CategoryRepository;
import com.side.bmarket.domain.prodcut.dto.SortType;
import com.side.bmarket.domain.prodcut.dto.response.ProductDto;
import com.side.bmarket.domain.prodcut.dto.response.ProductListDto;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.exception.NotFoundProductException;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    // 세부카테고리 기준 상품리스트 조회
    public ProductListDto findProductBySubCategory(Long subCategoryID, int currentPage) {
        SubCategorys subCategory = categoryRepository.findBySubCategorys(subCategoryID);
        Slice<Products> products = productRepository.findBySubCategoryId(subCategoryID, PageRequest.of(currentPage, 10));

        List<ProductDto> productList = products.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
        return ProductListDto.of(subCategory.getId(), subCategory.getSubCategoryName(), currentPage, productList);
    }

    // 상품 상세 조회
    public ProductDto findProductDetail(Long productID) {
        Products product = productRepository.findById(productID)
                .orElseThrow(() -> new NotFoundProductException("해당 상품이 없습니다"));
        return ProductDto.of(product);
    }

    // 상품 정렬
    public ProductListDto findProductSort(SortType sortType, int currentPage) {
        Sort sortPolicy = applySortPolicy(sortType);

        List<ProductDto> sortProduct = productRepository.findAll(PageRequest.of(currentPage, 10, sortPolicy)).stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
        return ProductListDto.of(1L, "정렬테스트", currentPage, sortProduct);
    }

    // 정렬 정책
    private Sort applySortPolicy(SortType sortType) {
        switch (sortType) {
            case LOW_PRICE:
                return Sort.by(Sort.Direction.ASC, "productPrice");
            case HIGH_PRICE:
                return Sort.by(Sort.Direction.DESC, "productPrice");
            case HIGHT_DISCOUNT:
                return Sort.by(Sort.Direction.DESC, "discountPrice");
            default:
                return Sort.by(Sort.Direction.ASC, "id");
        }
    }

    // 인기 상품 리스트
    public ProductListDto findProductPopularList() {
        Slice<Products> products = productRepository.findAllByOrderByQuantityAsc(PageRequest.of(0, 10));

        List<ProductDto> productList = products.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());

        return ProductListDto.builder()
                .product(productList)
                .currentPage(0)
                .build();
    }

    // 최대 할인 상품 리스트
    public ProductListDto findProductMaxDiscount() {
        Slice<Products> products = productRepository.findAllByOrderByDiscountRateDesc(PageRequest.of(0, 10));

        List<ProductDto> productList = products.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());

        return ProductListDto.builder()
                .product(productList)
                .currentPage(0)
                .build();
    }
}
