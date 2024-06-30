package com.mhmd.catalog_service.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.mhmd.catalog_service.TestcontainersConfiguration;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest(properties = {"spring.test.database.replace=none"})
// @Sql("/test-data.sql")
@Import(TestcontainersConfiguration.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldFindProductByCode() {
        ProductEntity productEntity = productRepository.findByCode("P100").orElseThrow();
        assertThat(productEntity.getCode()).isEqualTo("P100");
        assertThat(productEntity.getName()).isEqualTo("The Hunger Games");
        assertThat(productEntity.getDescription())
                .isEqualTo("Winning will make you famous. Losing means certain death...");
        assertThat(productEntity.getPrice()).isEqualTo(new BigDecimal("34.0"));
    }

    @Test
    void shouldReturnNullIfProductNotFound() {
        assertThat(productRepository.findByCode("NOT_EXISTING_CODE")).isEmpty();
    }
}
