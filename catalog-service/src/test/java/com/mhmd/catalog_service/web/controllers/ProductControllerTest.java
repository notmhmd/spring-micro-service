package com.mhmd.catalog_service.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import com.mhmd.catalog_service.AbstractIntegrationTest;
import com.mhmd.catalog_service.domain.ProductDto;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIntegrationTest {

    @Test
    void shouldGetAllProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(15))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldGetProductByCode() {
        ProductDto productDto = given().contentType(ContentType.JSON)
                .when()
                .get("api/products/{code}", "P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(ProductDto.class);
        assertThat(productDto.code()).isEqualTo("P100");
        assertThat(productDto.name()).isEqualTo("The Hunger Games");
        assertThat(productDto.description()).isEqualTo("Winning will make you famous. Losing means certain death...");
        assertThat(productDto.price()).isEqualTo(new BigDecimal("34.0"));
    }

    @Test
    void shouldReturnNotFoundWhenGetProductByInvalidCode() {
        given().contentType(ContentType.JSON)
                .when()
                .get("api/products/{code}", "NOT_EXISTING_CODE")
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code NOT_EXISTING_CODE not found"));
    }
}
