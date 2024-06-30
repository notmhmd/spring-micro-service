package com.mhmd.catalog_service.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ProductEntity}
 */
public record ProductDto(
        @NotEmpty(message = "product name is required") String name,
        String description,
        String imageUrl,
        @NotNull(message = "product price is required") BigDecimal price,
        @NotEmpty(message = "product code is required") String code)
        implements Serializable {}
