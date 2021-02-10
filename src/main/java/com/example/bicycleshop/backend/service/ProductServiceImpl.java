package com.example.bicycleshop.backend.service;


import com.example.bicycleshop.backend.model.Product;
import com.example.bicycleshop.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
}
