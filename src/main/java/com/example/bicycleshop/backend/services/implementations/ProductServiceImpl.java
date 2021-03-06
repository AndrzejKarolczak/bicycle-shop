package com.example.bicycleshop.backend.services.implementations;


import com.example.bicycleshop.backend.entities.Product;
import com.example.bicycleshop.backend.repositories.ProductRepository;
import com.example.bicycleshop.backend.services.ProductService;
import com.example.bicycleshop.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public List<Product> getProductType(Class<?> productType) {
		return productRepository.getProductsByProductType(productType);
	}
	
	@Override
	public Product getProductsById(Long id) {
		return productRepository.getProductsById(id).orElseThrow(() -> new NotFoundException(Product.class, id));
	}
}
