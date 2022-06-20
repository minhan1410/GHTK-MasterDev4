package com.minhan.productsmanagement.controller;

import com.minhan.productsmanagement.model.entity.ProductEntity;
import com.minhan.productsmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "page") int page, @RequestParam(value = "page_size") int pageSize) {
        return ResponseEntity.ok(productRepository.findAll(PageRequest.of(page, pageSize)));
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok(productRepository.save(productEntity));
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok(productRepository.save(productEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Thanh cong");
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam("price") Double price, @RequestParam(value = "name") String name) {
//        List<ProductEntity> names = productRepository.findByNameLike(name);
//        List<ProductEntity> prices = productRepository.findByPriceGreaterThan(price);
//        List<ProductEntity> namesAndPrices = productRepository.findByNameLikeAndPriceGreaterThanDesc(name, price);
        List<ProductEntity> products = productRepository.findProductEntity(price, name);
        List<ProductEntity> result = productRepository.search(price, name);
        return ResponseEntity.ok(result);
    }
}
