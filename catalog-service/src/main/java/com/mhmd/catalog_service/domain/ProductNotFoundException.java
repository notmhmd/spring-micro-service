package com.mhmd.catalog_service.domain;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException forCode(String code) {
        return new ProductNotFoundException("Product with code " + code + " not found");
    }
}
