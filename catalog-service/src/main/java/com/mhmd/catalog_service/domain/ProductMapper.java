package com.mhmd.catalog_service.domain;

class ProductMapper {

    static ProductDto toProduct(ProductEntity productEntity) {
        return new ProductDto(
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice(),
                productEntity.getCode());
    }
}
