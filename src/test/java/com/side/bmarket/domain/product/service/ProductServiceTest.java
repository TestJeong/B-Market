package com.side.bmarket.domain.product.service;

import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.repository.SubCategoryRepository;
import com.side.bmarket.domain.category.support.SubCategoryFixture;
import com.side.bmarket.domain.prodcut.dto.SortType;
import com.side.bmarket.domain.prodcut.dto.response.ProductListDto;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import com.side.bmarket.domain.prodcut.service.ProductService;
import com.side.bmarket.domain.product.support.ProductFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    PageRequest pageRequest = PageRequest.of(0, 10);
    SubCategorys subCategorys = SubCategoryFixture.createSubCategoryName("세부카테고리1");
    Products product1 = ProductFixture.createProduct("상품2", 1000, 100, 0, 10);
    Products product2 = ProductFixture.createProduct("상품3", 1400, 200, 0, 10);
    Products product3 = ProductFixture.createProduct("상품4", 1200, 300, 0, 10);

    @InjectMocks
    ProductService productService;

    @Mock
    SubCategoryRepository subCategoryRepository;

    @Mock
    ProductRepository productRepository;


    @DisplayName("세부카테고리 기준 상품리스트를 기본정렬 순으로 조회합니다.")
    @Test
    void findProductBySubCategorySortDefaultTest() {
        // given
        List<Products> productList = List.of(product1, product2, product3);
        Slice<Products> products = new PageImpl<>(productList, pageRequest, productList.size());

        given(subCategoryRepository.findById(any())).willReturn(Optional.ofNullable(subCategorys));
        given(productRepository.findBySubCategoryId(any(), any())).willReturn(products);

        // when
        ProductListDto productListDto = productService.findProductBySubCategory(1L, SortType.DEFAULT, 0);

        // then
        assertThat(productListDto.getProduct().get(0).getProductName()).isEqualTo("상품2");
        assertThat(productListDto.getProduct().get(1).getProductName()).isEqualTo("상품3");
        assertThat(productListDto.getProduct().get(2).getProductName()).isEqualTo("상품4");
    }

    @DisplayName("세부카테고리 기준 상품리스트를 낮은 가격 정렬 순으로 조회합니다.")
    @Test
    void findProductBySubCategorySortLowPriceTest() {
        // given
        List<Products> productList = Arrays.asList(product1, product2, product3);
        productList.sort(Comparator.comparing(Products::getProductPrice));
        Slice<Products> products = new PageImpl<>(productList, pageRequest, productList.size());

        given(subCategoryRepository.findById(any())).willReturn(Optional.ofNullable(subCategorys));
        given(productRepository.findBySubCategoryId(any(), any())).willReturn(products);

        // when
        ProductListDto productListDto = productService.findProductBySubCategory(1L, SortType.LOW_PRICE, 0);

        // then
        assertThat(productListDto.getProduct().get(0).getProductName()).isEqualTo("상품2");
        assertThat(productListDto.getProduct().get(1).getProductName()).isEqualTo("상품4");
        assertThat(productListDto.getProduct().get(2).getProductName()).isEqualTo("상품3");
    }

    @DisplayName("세부카테고리 기준 상품리스트를 높은가격 순으로 조회합니다.")
    @Test
    void findProductBySubCategorySortHighPriceTest() {
        // given
        List<Products> productList = Arrays.asList(product1, product2, product3);
        productList.sort(Comparator.comparingInt(Products::getProductPrice).reversed());
        Slice<Products> products = new PageImpl<>(productList, pageRequest, productList.size());

        given(subCategoryRepository.findById(any())).willReturn(Optional.ofNullable(subCategorys));
        given(productRepository.findBySubCategoryId(any(), any())).willReturn(products);

        // when
        ProductListDto productListDto = productService.findProductBySubCategory(1L, SortType.HIGH_PRICE, 0);

        // then
        assertThat(productListDto.getProduct().get(0).getProductName()).isEqualTo("상품3");
        assertThat(productListDto.getProduct().get(1).getProductName()).isEqualTo("상품4");
        assertThat(productListDto.getProduct().get(2).getProductName()).isEqualTo("상품2");
    }

    @DisplayName("세부카테고리 기준 상품리스트를 높은 할인가격으로 조회합니다.")
    @Test
    void findProductBySubCategorySortHighDiscountTest() {
        // given
        List<Products> productList = Arrays.asList(product1, product2, product3);
        productList.sort(Comparator.comparingInt(Products::getDiscountPrice).reversed());
        Slice<Products> products = new PageImpl<>(productList, pageRequest, productList.size());

        given(subCategoryRepository.findById(any())).willReturn(Optional.ofNullable(subCategorys));
        given(productRepository.findBySubCategoryId(any(), any())).willReturn(products);

        // when
        ProductListDto productListDto = productService.findProductBySubCategory(1L, SortType.HIGHT_DISCOUNT, 0);

        // then
        assertThat(productListDto.getProduct().get(0).getProductName()).isEqualTo("상품4");
        assertThat(productListDto.getProduct().get(1).getProductName()).isEqualTo("상품3");
        assertThat(productListDto.getProduct().get(2).getProductName()).isEqualTo("상품2");
    }
}
