package com.minhan.productsmanagement.controller;

import com.minhan.productsmanagement.model.entity.CategoryEntity;
import com.minhan.productsmanagement.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "page") int page, @RequestParam(value = "page_size") int pageSize) {
        return ResponseEntity.ok(categoryServiceImpl.get(PageRequest.of(page, pageSize)));
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody CategoryEntity categoryEntity) {
        return ResponseEntity.ok(categoryServiceImpl.create(categoryEntity));
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody CategoryEntity categoryEntity) {
        CategoryEntity result = categoryServiceImpl.update(categoryEntity);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body("ID khong ton tai");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return categoryServiceImpl.delete(id) ? ResponseEntity.ok("Thanh cong") : ResponseEntity.badRequest().body("ID khong ton tai");
    }

}
