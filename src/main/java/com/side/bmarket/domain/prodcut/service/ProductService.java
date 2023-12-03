package com.side.bmarket.domain.prodcut.service;

import com.side.bmarket.domain.category.entity.SubCategorys;
import com.side.bmarket.domain.category.repository.CategoryRepository;
import com.side.bmarket.domain.prodcut.dto.ProductByCategoryDTO;
import com.side.bmarket.domain.prodcut.dto.ProductDTO;
import com.side.bmarket.domain.prodcut.entity.Products;
import com.side.bmarket.domain.prodcut.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductByCategoryDTO.response getProductBySubcCategory(Long subCategoryID) {
        SubCategorys subCategory = categoryRepository.findBySubCategorys(subCategoryID);
        List<Products> products = productRepository.findByProduct(subCategoryID);
        List<ProductDTO.response> productList = products.stream()
                .map(ProductDTO.response::new)
                .collect(Collectors.toList());

        return new ProductByCategoryDTO.response(
                subCategory.getId(),
                subCategory.getSubCategoryName(),
                productList
        );

    }

    public Products getProduct(Long productID) {
        return productRepository.findOneProduct(productID);
    }
}
