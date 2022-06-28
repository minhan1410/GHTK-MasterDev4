package com.minhan.productsmanagement.controller;

import com.minhan.productsmanagement.model.dto.CategoryDto;
import com.minhan.productsmanagement.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "page") int page, @RequestParam(value = "page_size") int pageSize) {
        return ResponseEntity.ok(categoryServiceImpl.get(page, pageSize));
    }

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryServiceImpl.create(categoryDto));
    }

    @PutMapping("")
    public ResponseEntity update(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryServiceImpl.update(categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.ok(categoryServiceImpl.delete(id));
    }

}
