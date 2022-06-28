package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.constants.StatusConstant;
import com.minhan.productsmanagement.model.dto.ProductDto;
import com.minhan.productsmanagement.model.entity.ProductEntity;
import com.minhan.productsmanagement.model.error.ExceptionObject;
import com.minhan.productsmanagement.model.response.Pagination;
import com.minhan.productsmanagement.model.response.ResponseObject;
import com.minhan.productsmanagement.model.response.ResponsePage;
import com.minhan.productsmanagement.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponsePage get(int page, int pageSize) {
        Page<ProductDto> productDtos = productRepository.findByStatusEquals(StatusConstant.ACTIVE.getValue(), PageRequest.of(page, pageSize))
                .map(productEntity -> modelMapper.map(productEntity, ProductDto.class));

        return ResponsePage.builder().success(true).message("Thanh cong").data(productDtos.getContent())
                .pagination(Pagination.builder().page(page).pagesize(pageSize)
                        .total(productDtos.getTotalElements()).build()).build();
    }

    @Override
    public ResponseObject create(ProductDto newProduct) {
        LocalDateTime time = LocalDateTime.now();
        ProductEntity productEntity = modelMapper.map(newProduct, ProductEntity.class);
        productEntity.setStatus(StatusConstant.ACTIVE.getValue());
        productEntity.setCreatedAt(time);
        productEntity.setModifiedAt(time);
        productEntity.setCode(newProduct.getCategoryId() + "." + newProduct.getSku() + "." + productEntity.getCreatedAt());

        return ResponseObject.builder().success(true).message("Thanh cong")
                .data(modelMapper.map(productRepository.save(productEntity), ProductDto.class)).build();
    }

    @Override
    public ResponseObject update(ProductDto product) {
        if (!categoryServiceImpl.categoryIsPresentById(product.getCategoryId())) {
            throw ExceptionObject.builder().message("ID Category khong ton tai").build();
        }

        Optional<ProductEntity> optionalProductEntity = productRepository.findById(product.getId());
        if (!optionalProductEntity.isPresent()) {
            throw ExceptionObject.builder().message("ID Product khong ton tai").build();
        }

        ProductEntity productEntityRepository = optionalProductEntity.get();
        if (productEntityRepository.getStatus() == StatusConstant.INACTIVE.getValue()) {
            throw ExceptionObject.builder().message("ID Product da xoa truoc do").build();
        }

        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        productEntity.setStatus(1);
        productEntity.setCode(productEntityRepository.getCode());
        productEntity.setCreatedAt(productEntityRepository.getCreatedAt());
        productEntity.setModifiedAt(LocalDateTime.now());

        return ResponseObject.builder().success(true).message("Thanh cong")
                .data(modelMapper.map(productRepository.save(productEntity), ProductDto.class)).build();
    }

    @Override
    public ResponseObject delete(Long productId) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
        if (!optionalProductEntity.isPresent()) {
            throw ExceptionObject.builder().message("ID Product khong ton tai").build();
        }

        ProductEntity productEntityRepository = optionalProductEntity.get();
        if (productEntityRepository.getStatus() == StatusConstant.INACTIVE.getValue()) {
            throw ExceptionObject.builder().message("ID Product da xoa truoc do").build();
        }

        productEntityRepository.setStatus(StatusConstant.INACTIVE.getValue());
        productRepository.save(productEntityRepository);

        return ResponseObject.builder().success(true).message("Thanh cong").data(null).build();
    }

    @Override
    public ResponsePage search(String name, Double price, int page, int pageSize) {
        Page<ProductDto> productDtos = productRepository.findByNameContainingAndPriceGreaterThanOrderByPriceDesc(name, price,
                PageRequest.of(page, pageSize)).map(productEntity -> modelMapper.map(productEntity, ProductDto.class));

        return ResponsePage.builder().success(true).message("Thanh cong").data(productDtos.getContent())
                .pagination(Pagination.builder().page(page).pagesize(pageSize)
                        .total(productDtos.getTotalElements()).build()).build();
    }
}
