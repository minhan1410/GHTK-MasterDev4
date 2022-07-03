package com.minhan.productsmanagement.model.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;
    private Integer status;
//    @OneToMany(mappedBy = "category")
//    private List<ProductEntity> product;
}
