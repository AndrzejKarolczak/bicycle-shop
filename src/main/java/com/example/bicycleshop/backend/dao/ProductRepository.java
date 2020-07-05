package com.example.bicycleshop.backend.dao;

import com.example.bicycleshop.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
