package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.model.dto.ProductDto;
import com.minhan.productsmanagement.model.response.ResponseObject;
import com.minhan.productsmanagement.model.response.ResponsePage;

public interface ProductService {
    ResponsePage get(int page, int pageSize);

    ResponseObject create(ProductDto newProduct);

    ResponseObject update(ProductDto product);

    ResponseObject delete(Long productId);

    ResponsePage search(String name, Double price, int page, int pageSize);
}
