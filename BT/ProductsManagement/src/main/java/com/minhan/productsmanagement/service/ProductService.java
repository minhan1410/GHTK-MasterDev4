package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductEntity> get(Pageable pageable);

    ProductEntity create(ProductEntity newProduct);

    ProductEntity update(ProductEntity product);

    Boolean delete(Long productId);

    Page<ProductEntity> search(Pageable pageable, String name, Double price);
}
