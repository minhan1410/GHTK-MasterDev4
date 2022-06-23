package com.minhan.productsmanagement.controller;

import com.minhan.productsmanagement.model.entity.ProductEntity;
import com.minhan.productsmanagement.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "page") int page, @RequestParam(value = "page_size") int pageSize) {
        return ResponseEntity.ok(productServiceImpl.get(PageRequest.of(page, pageSize)));
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody ProductEntity productEntity) {
        ProductEntity result = productServiceImpl.create(productEntity);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body("ID Category khong ton tai");
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody ProductEntity productEntity) {
        ProductEntity result = productServiceImpl.update(productEntity);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body("ID Category hoac ID Product khong ton tai");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return productServiceImpl.delete(id) ? ResponseEntity.ok("Thanh cong") : ResponseEntity.badRequest().body("ID khong ton tai");
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam("page") int page, @RequestParam("page_size") int pageSize, @RequestParam("price") Double price, @RequestParam(value = "name") String name) {
        return ResponseEntity.ok(productServiceImpl.search(PageRequest.of(page, pageSize), name, price));
    }

}
