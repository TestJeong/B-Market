package com.side.bmarket.domain.prodcut.api;

import com.side.bmarket.domain.prodcut.dto.ProductByCategoryDTO;
import com.side.bmarket.domain.prodcut.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    public ProductByCategoryDTO.response getProduct(@RequestParam("subCategoryID") Long subCategoryID) {
        return productService.getProductBySubcCategory(subCategoryID);
    }
}
