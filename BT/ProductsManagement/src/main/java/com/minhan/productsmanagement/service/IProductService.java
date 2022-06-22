package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.model.entity.ProductEntity;
import com.minhan.productsmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IProductService implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ICategoryService iCategoryService;

    @Override
    public Page<ProductEntity> get(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public ProductEntity create(ProductEntity newProduct) {
        return iCategoryService.categoryIsPresentById(newProduct.getCategory().getId()) ? productRepository.save(newProduct) : null;
    }

    @Override
    public ProductEntity update(ProductEntity product) {
        boolean checkCategory = iCategoryService.categoryIsPresentById(product.getCategory().getId());
        if (!checkCategory) {
            return null;
        }
        return productRepository.findById(product.getId()).isPresent() ? productRepository.save(product) : null;
    }

    @Override
    public Boolean delete(Long productId) {
        if (productRepository.findById(productId).isPresent()) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public Page<ProductEntity> search(Pageable pageable, String name, Double price) {
//        List<ProductEntity> names = productRepository.findByNameContaining(name);
//        List<ProductEntity> prices = productRepository.findByPriceGreaterThan(price);

        Page<ProductEntity> c1 = productRepository.findByNameContainingAndPriceGreaterThanOrderByPriceDesc(pageable, name, price);
//        List<ProductEntity> c2 = productRepository.findProductEntity(price, name);
//        List<ProductEntity> c3 = productRepository.search(price, name);

        return c1;
    }
}
