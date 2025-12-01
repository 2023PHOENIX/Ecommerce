package com.ecomm.pct.repository;

import com.ecomm.pct.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    boolean existsByName(String name);
}
