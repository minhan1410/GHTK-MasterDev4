package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.model.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryEntity> get(Pageable pageable);

    CategoryEntity create(CategoryEntity newCategory);

    CategoryEntity update(CategoryEntity category);

    Boolean delete(Long categoryId);
}
