package com.minhan.productsmanagement.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "province")
@Data
public class ProvinceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
}
