package com.mhmd.catalog_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "product code is required")
    private String code;

    @Column(nullable = false)
    @NotEmpty(message = "product name is required")
    private String name;

    private String description;

    private String imageUrl;

    @Column(nullable = false)
    @NotNull(message = "product price is required") @DecimalMin(message = "product price must be greater than 0.1", value = "0.1")
    private BigDecimal price;

    public ProductEntity() {}

    public ProductEntity(String code, String description, Long id, String imageUrl, String name, BigDecimal price) {
        this.code = code;
        this.description = description;
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
    }

    public @NotEmpty(message = "product code is required") String getCode() {
        return code;
    }

    public void setCode(@NotEmpty(message = "product code is required") String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "product name is required") String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(
            @NotNull(message = "product price is required") @DecimalMin(message = "product price must be greater than 0.1", value = "0.1")
                    BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
