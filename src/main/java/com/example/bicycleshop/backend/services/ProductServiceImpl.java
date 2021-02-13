package com.example.bicycleshop.backend.services;


import com.example.bicycleshop.backend.entities.Product;
import com.example.bicycleshop.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
	@Override
	public List<Product> getProductType(String productType) {
		return productRepository.getProductType(productType) ;
	}
	
}