package com.mhmd.catalog_service.domain;

import com.mhmd.catalog_service.ApplicationProperties;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties properties;

    ProductService(ProductRepository productRepository, ApplicationProperties properties) {
        this.productRepository = productRepository;
        this.properties = properties;
    }

    public PagedResult<ProductDto> getAllProducts(int page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        page = page <= 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(page, properties.pageSize(), sort);
        Page<ProductDto> products = productRepository.findAll(pageable).map(ProductMapper::toProduct);
        return new PagedResult<>(
                products.getContent(),
                products.getTotalElements(),
                products.getNumber() + 1,
                products.getTotalPages(),
                products.isFirst(),
                products.isLast(),
                products.hasNext(),
                products.hasPrevious());
    }

    public Optional<ProductDto> getProductByCode(String code) {
        return this.productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
