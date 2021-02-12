package com.example.bicycleshop;

import com.example.bicycleshop.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestController {
	
	private final ProductService productService;
	
	@Autowired
	public RequestController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping({"/", "/products"})
	public String showProducts(Model model) {
		model.addAttribute("items", productService.getProducts());
		return "product-list";
	}
	
	@GetMapping({"/basket"})
	public String basket() {
		return "basket-view";
	}
	
	@PostMapping("/submit-basket")
	public String submitBasket(){
		
		return null;
	}
	
}
