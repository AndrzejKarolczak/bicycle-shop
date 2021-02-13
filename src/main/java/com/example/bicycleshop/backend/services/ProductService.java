package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.Product;

import java.util.List;

public interface ProductService {
	List<Product> getProducts();
	List<Product> getProductType(String productType);
}