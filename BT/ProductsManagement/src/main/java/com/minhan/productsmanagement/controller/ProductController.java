package com.minhan.productsmanagement.controller;

import com.minhan.productsmanagement.model.dto.ProductDto;
import com.minhan.productsmanagement.model.response.ResponseObject;
import com.minhan.productsmanagement.model.response.ResponsePage;
import com.minhan.productsmanagement.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("")
    public ResponseEntity<ResponsePage> get(@RequestParam(value = "page") int page, @RequestParam(value = "page_size") int pageSize) {
        return ResponseEntity.ok(productServiceImpl.get(page, pageSize));
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productServiceImpl.create(productDto));
    }

    @PutMapping("")
    public ResponseEntity<ResponseObject> update(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productServiceImpl.update(productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.ok(productServiceImpl.delete(id));
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam("page") int page, @RequestParam("page_size") int pageSize, @RequestParam("price") Double price, @RequestParam(value = "name") String name) {
        return ResponseEntity.ok(productServiceImpl.search(name, price, page, pageSize));
    }

}
