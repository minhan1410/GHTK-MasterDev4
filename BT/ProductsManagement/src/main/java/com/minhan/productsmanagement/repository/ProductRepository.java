package com.minhan.productsmanagement.repository;

import com.minhan.productsmanagement.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameContaining(String name);

    List<ProductEntity> findByPriceGreaterThan(Double price);

    List<ProductEntity> findByNameContainingAndPriceGreaterThanOrderByPriceDesc(String name, Double price);

    @Query("select p from ProductEntity p where p.price > :price and p.name like '%:name' ORDER BY p.price desc")
    List<ProductEntity> findProductEntity(@Param("price") Double price, @Param("name") String name);

    @Query(value = "select * from product as p where p.price > ?1 and p.name like CONCAT('%', ?2, '%') ORDER BY p.price desc", nativeQuery = true)
    List<ProductEntity> search(Double price, String name);

}
