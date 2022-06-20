package com.minhan.productsmanagement.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "warehoure_product")
@Data
public class WarehoureProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long warehoureId;
    private Integer inventory;
    private Integer totalImport;
    private Integer totalExport;
    private Date startDate;
    private Date expiredDate;
}
