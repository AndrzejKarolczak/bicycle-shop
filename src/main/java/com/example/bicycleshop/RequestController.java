package com.example.bicycleshop;

import com.example.bicycleshop.backend.entities.enums.ProductType;
import com.example.bicycleshop.backend.services.CountryService;
import com.example.bicycleshop.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.UUID;

@Controller
public class RequestController {
	private final HashMap<String, Session> sessions = new HashMap<>();
	private final ProductService productService;
	private final CountryService countryService;
	
	@Autowired
	public RequestController(ProductService productService, CountryService countryService) {
		this.productService = productService;
		this.countryService = countryService;
	}
	
	@GetMapping({"/", "/start", "/index"})
	public String showStartPage() {
		return "start";
	}
	
	@GetMapping("/products")
	public String showProductsPage(@RequestParam String productType, Model model) {
		if(ProductType.valueOf(productType) == ProductType.BICYCLE){
			model.addAttribute("productType", "rowerów");
		} else {
			model.addAttribute("productType", "części rowerowych");
		}
		model.addAttribute("items", productService.getProductType(productType));
		
		return "product-list-view";
	}
	
	@GetMapping({"/basket"})
	public String showBasketContentsPage() {
		return "basket-contents-view";
	}
	
	@GetMapping("/order")
	public String submitBasket(Model model) {
		String sessionId = UUID.randomUUID().toString();
//		sessions.put(sessionId, new Session(sessionId, basketContents));
		model.addAttribute("session", sessionId);
		model.addAttribute("countries", countryService.getCountries());
		model.addAttribute("customerDetails", new CustomerDetailsForm());
		return "customer-details-view";
	}
	
	@PostMapping("/customer-details")
	public String submitIndividualDetails(@ModelAttribute("employee") CustomerDetailsForm form, Model model) {
		
		return "payment-details-view";
	}
	
	@PostMapping("/firm-details")
	public String submitFirmDetails(Model model) {
		
		return "payment-details-view";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "access-denied-view";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login-view";
	}
}
