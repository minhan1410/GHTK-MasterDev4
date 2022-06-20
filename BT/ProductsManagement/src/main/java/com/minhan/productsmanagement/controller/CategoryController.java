package com.minhan.productsmanagement.controller;

import com.minhan.productsmanagement.model.entity.CategoryEntity;
import com.minhan.productsmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "page") int page, @RequestParam(value = "page_size") int pageSize) {
//    List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(categoryEntities);
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody CategoryEntity categoryEntity) {
        return ResponseEntity.ok(categoryRepository.save(categoryEntity));
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody CategoryEntity categoryEntity) {
        return ResponseEntity.ok(categoryRepository.save(categoryEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        categoryRepository.delete(categoryEntity.get());
        return ResponseEntity.ok("Thanh cong");
    }

}
