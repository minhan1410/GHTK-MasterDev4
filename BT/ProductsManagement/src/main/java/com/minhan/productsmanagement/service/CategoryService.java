package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.model.dto.CategoryDto;
import com.minhan.productsmanagement.model.response.ResponseObject;
import com.minhan.productsmanagement.model.response.ResponsePage;

public interface CategoryService {
    ResponsePage get(int page, int pageSize);

    ResponseObject create(CategoryDto newCategory);

    ResponseObject update(CategoryDto category);

    ResponseObject delete(Long categoryId);
}
