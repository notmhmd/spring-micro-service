package com.mhmd.catalog_service.web.controllers;

import com.mhmd.catalog_service.domain.PagedResult;
import com.mhmd.catalog_service.domain.ProductDto;
import com.mhmd.catalog_service.domain.ProductNotFoundException;
import com.mhmd.catalog_service.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<ProductDto> getProducts(@RequestParam(required = false, defaultValue = "1") Integer page) {
        return productService.getAllProducts(page);
    }

    @GetMapping("/{code}")
    ResponseEntity<ProductDto> getProductByCode(@PathVariable String code) {
        return this.productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
