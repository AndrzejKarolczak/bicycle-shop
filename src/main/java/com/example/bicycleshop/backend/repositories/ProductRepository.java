package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
