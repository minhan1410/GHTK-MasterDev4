package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.constants.StatusConstant;
import com.minhan.productsmanagement.model.dto.CategoryDto;
import com.minhan.productsmanagement.model.entity.CategoryEntity;
import com.minhan.productsmanagement.model.error.ExceptionObject;
import com.minhan.productsmanagement.model.response.Pagination;
import com.minhan.productsmanagement.model.response.ResponseObject;
import com.minhan.productsmanagement.model.response.ResponsePage;
import com.minhan.productsmanagement.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponsePage get(int page, int pageSize) {
        Page<CategoryDto> categoryDtos = categoryRepository.findByStatusEquals(StatusConstant.ACTIVE.getValue(), PageRequest.of(page, pageSize))
                .map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDto.class));

        return ResponsePage.builder().success(true).message("Thanh cong").data(categoryDtos.getContent())
                .pagination(Pagination.builder().page(page).pagesize(pageSize)
                        .total(categoryDtos.getTotalElements()).build()).build();
    }

    @Override
    public ResponseObject create(CategoryDto newCategory) {
        CategoryEntity categoryEntity = modelMapper.map(newCategory, CategoryEntity.class);
        categoryEntity.setCode(categoryEntity.getName() + "." + LocalDateTime.now());
        categoryEntity.setStatus(StatusConstant.ACTIVE.getValue());

        return ResponseObject.builder().success(true).message("Thanh cong")
                .data(modelMapper.map(categoryRepository.save(categoryEntity), CategoryDto.class)).build();
    }

    @Override
    public ResponseObject update(CategoryDto category) {
        Optional<CategoryEntity> optional = categoryRepository.findById(category.getId());
        if (!optional.isPresent()) {
            throw ExceptionObject.builder().message("ID Category khong ton tai").build();
        }

        CategoryEntity categoryEntityRepository = optional.get();
        if (categoryEntityRepository.getStatus() == StatusConstant.INACTIVE.getValue()) {
            throw ExceptionObject.builder().message("ID Category da xoa truoc do").build();
        }

        CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);
        categoryEntity.setCode(categoryEntityRepository.getCode());
        categoryEntity.setStatus(categoryEntityRepository.getStatus());

        return ResponseObject.builder().success(true).message("Thanh cong")
                .data(modelMapper.map(categoryRepository.save(categoryEntity), CategoryDto.class)).build();
    }

    @Override
    public ResponseObject delete(Long categoryId) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
        if (!categoryEntity.isPresent()) {
            throw ExceptionObject.builder().message("ID Category khong ton tai").build();
        }

        CategoryEntity categoryEntityRepository = categoryEntity.get();
        if (categoryEntityRepository.getStatus() == StatusConstant.INACTIVE.getValue()) {
            throw ExceptionObject.builder().message("ID Category da xoa truoc do").build();
        }

        categoryEntityRepository.setStatus(StatusConstant.INACTIVE.getValue());
        categoryRepository.save(categoryEntityRepository);

        return ResponseObject.builder().success(true).message("Thanh cong").data(null).build();
    }

    public Boolean categoryIsPresentById(Long id) {
        return categoryRepository.findById(id).isPresent();
    }

}
