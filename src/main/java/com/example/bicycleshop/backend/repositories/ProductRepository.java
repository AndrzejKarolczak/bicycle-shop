package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(nativeQuery = true,
			value = "SELECT * FROM products WHERE product_type = :productType")
	List<Product> getProductType(@Param("productType") String productType);
}
