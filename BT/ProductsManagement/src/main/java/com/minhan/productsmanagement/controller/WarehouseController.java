package com.minhan.productsmanagement.controller;

import com.minhan.productsmanagement.model.dto.WarehoureDto;
import com.minhan.productsmanagement.service.WarehouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseServiceImpl warehouseService;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "page") int page, @RequestParam(value = "page_size") int pageSize) {
        return ResponseEntity.ok(warehouseService.get(page, pageSize));
    }

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody WarehoureDto warehoure) {
        return ResponseEntity.ok(warehouseService.create(warehoure));
    }

    @PutMapping("")
    public ResponseEntity update(@Valid @RequestBody WarehoureDto warehoure) {
        return ResponseEntity.ok(warehouseService.update(warehoure));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.delete(id));
    }
}
