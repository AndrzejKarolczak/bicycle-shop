package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p JOIN FETCH p.manufacturer WHERE TYPE(p) = :productType")
	List<Product> getProductsByProductType(@Param("productType") Class<?> productType);
	
	@Query("SELECT p FROM Product p JOIN FETCH p.manufacturer WHERE p.productId = :productId")
	Optional<Product> getProductsById(@Param("productId") Long productId);
	
}
