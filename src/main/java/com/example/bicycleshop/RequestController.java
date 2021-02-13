package com.example.bicycleshop;

import com.example.bicycleshop.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class RequestController {
	private final HashMap<String, Session> sessions = new HashMap<>();
	private static long sessionNumber = 0;
	private final ProductService productService;
	
	@Autowired
	public RequestController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping({"/", "/products"})
	public String showProductsPage(Model model) {
		model.addAttribute("items", productService.getProducts());
		return "product-list-view";
	}
	
	@GetMapping({"/basket"})
	public String showBasketContentsPage() {
		return "basket-contents-view";
	}
	
	@PostMapping("/order")
	public String submitBasket(@RequestBody List<BasketItem> basketContents) {
		String sessionId = UUID.fromString(String.valueOf(++sessionNumber)).toString();
		sessions.put(sessionId, new Session(sessionId, basketContents));
		
		return "provide-customer-details-view";
	}
	
	@GetMapping("new-account")
	public String showNewCustomerDetailsPage(){
		
		return "customer-details-view";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "access-denied-view";
	}
	
	@GetMapping("/login")
	public String showLoginPage(){
		return "login-view";
	}
}
