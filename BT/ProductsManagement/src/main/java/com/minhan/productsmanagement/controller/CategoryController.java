package com.minhan.productsmanagement.controller;

import com.minhan.productsmanagement.model.entity.CategoryEntity;
import com.minhan.productsmanagement.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/category")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "page") int page, @RequestParam(value = "page_size") int pageSize) {
        return ResponseEntity.ok(iCategoryService.get(PageRequest.of(page, pageSize)));
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody CategoryEntity categoryEntity) {
        return ResponseEntity.ok(iCategoryService.create(categoryEntity));
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody CategoryEntity categoryEntity) {
        CategoryEntity result = iCategoryService.update(categoryEntity);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body("ID khong ton tai");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return iCategoryService.delete(id) ? ResponseEntity.ok("Thanh cong") : ResponseEntity.badRequest().body("ID khong ton tai");
    }

}
