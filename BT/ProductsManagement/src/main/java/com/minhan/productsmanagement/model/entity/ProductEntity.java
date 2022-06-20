package com.minhan.productsmanagement.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String sku;
    private Integer status;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
