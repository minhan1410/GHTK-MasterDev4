package com.minhan.productsmanagement.repository;

import com.minhan.productsmanagement.model.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long> {
    Optional<ProvinceEntity> findById(Long id);
}
