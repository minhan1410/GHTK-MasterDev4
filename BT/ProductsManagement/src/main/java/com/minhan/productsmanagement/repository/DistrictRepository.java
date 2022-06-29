package com.minhan.productsmanagement.repository;

import com.minhan.productsmanagement.model.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    Optional<DistrictEntity> findById(Long id);
}
