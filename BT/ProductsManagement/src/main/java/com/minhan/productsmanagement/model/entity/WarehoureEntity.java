package com.minhan.productsmanagement.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "warehoure")
@Data
public class WarehoureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Long provinceId;
    private Integer status;
    private Long distinctId;
}
