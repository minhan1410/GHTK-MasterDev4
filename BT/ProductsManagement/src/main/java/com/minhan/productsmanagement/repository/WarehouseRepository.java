package com.minhan.productsmanagement.repository;

import com.minhan.productsmanagement.model.entity.WarehoureEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehoureEntity, Long> {
    Page<WarehoureEntity> findByStatusEquals(Integer status, Pageable pageable);
}
