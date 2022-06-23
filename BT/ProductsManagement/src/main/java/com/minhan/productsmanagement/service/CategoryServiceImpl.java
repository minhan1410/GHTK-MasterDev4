package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.model.entity.CategoryEntity;
import com.minhan.productsmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<CategoryEntity> get(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public CategoryEntity create(CategoryEntity newCategory) {
        return categoryRepository.save(newCategory);
    }

    @Override
    public CategoryEntity update(CategoryEntity category) {
        return categoryRepository.findById(category.getId()).isPresent() ? categoryRepository.save(category) : null;
    }

    @Override
    public Boolean delete(Long categoryId) {
        if (categoryRepository.findById(categoryId).isPresent()) {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }

    public Boolean categoryIsPresentById(Long id) {
        return categoryRepository.findById(id).isPresent();
    }

}
