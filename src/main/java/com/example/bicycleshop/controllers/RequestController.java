package com.example.bicycleshop.controllers;

import com.example.bicycleshop.backend.entities.Bicycle;
import com.example.bicycleshop.backend.entities.BicyclePart;
import com.example.bicycleshop.backend.entities.Order;
import com.example.bicycleshop.backend.entities.enums.ProductType;
import com.example.bicycleshop.backend.services.CountryService;
import com.example.bicycleshop.backend.services.CustomerDetailsService;
import com.example.bicycleshop.backend.services.OrderService;
import com.example.bicycleshop.backend.services.ProductService;
import com.example.bicycleshop.dtos.CustomerDetailsDto;
import com.example.bicycleshop.dtos.PaymentDetailsDto;
import com.example.bicycleshop.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class RequestController {
	private final ProductService productService;
	private final CountryService countryService;
	private final CustomerDetailsService customerDetailsService;
	private final OrderService orderService;
	
	@Autowired
	public RequestController(ProductService productService, CountryService countryService, CustomerDetailsService
		customerDetailsService, OrderService orderService) {
		this.productService = productService;
		this.countryService = countryService;
		this.customerDetailsService = customerDetailsService;
		this.orderService = orderService;
	}
	
	@GetMapping({"/", "/start", "/index"})
	public String showStartPage() {
		return "start-view";
	}
	
	@GetMapping("/products")
	public String showProductsPage(@RequestParam("productType") String productType, Model model) {
		ProductType product = ProductType.valueOf(productType);
		Class<?> productClassName;
		if (product == ProductType.BICYCLE) {
			productClassName = Bicycle.class;
			model.addAttribute("productType", "rowerów");
		} else if (product == ProductType.PART) {
			productClassName = BicyclePart.class;
			model.addAttribute("productType", "części rowerowych");
		} else {
			throw new NotFoundException(ProductType.class, productType); //TODO
		}
		model.addAttribute("items", productService.getProductType(productClassName));
		
		return "product-list-view";
	}
	
	@GetMapping({"/basket"})
	public String showBasketContentsPage() {
		return "basket-contents-view";
	}
	
	@GetMapping("/customer-details")
	public String showCustomerDetailsPage(Model model) {
		String sessionId = UUID.randomUUID().toString();
		model.addAttribute("countries", countryService.getHtmlListOfCountries());
		model.addAttribute("customerDetails", new CustomerDetailsDto(sessionId));
		return "customer-details-view";
	}
	
	@PostMapping("/payment-details")
	public String showPaymentDetailsPage(@ModelAttribute("customerDetails") CustomerDetailsDto form, Model model) {
		Order order = orderService.save(form);
		
		model.addAttribute("session", form.getSessionId());
		//model.addAttribute("paymentDetails", new PaymentDetailsDto()); //TODO
		return "payment-details-view";
	}
	
	@PostMapping("payment-successful")
	public String showPaymentSuccessfulPage(@ModelAttribute("paymentDetails") PaymentDetailsDto form, Model model) {
		model.addAttribute("title", "Transakcja zrealizowana");
		model.addAttribute("message", "Transakcja została zrealizowana");
		return "message-view";
	}
	
	@RequestMapping("order-cancelled")
	public String showCancelledPage(@RequestParam("session") String sessionId, Model model) {
		model.addAttribute("title", "Transakcja anulowana");
		model.addAttribute("message", "Transakcja została anulowana");
		return "message-view";
	}
	
	@GetMapping("/saved-customer-details")
	public String showSavedCustomerDetailsPage(Model model) {
		model.addAttribute("countries", countryService.getCountries());
		return "saved-customer-details-view";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "access-denied-view";
	}
	
	@GetMapping("/new-account")
	public String showNewAccountPage(Model model) {
		model.addAttribute("countries", countryService.getCountries());
		return "new-account-view";
	}
	
	@PostMapping("save-customer-details")
	public String showCustomerDetailsSavedPage(@ModelAttribute("customerDetails") CustomerDetailsDto form, Model model) {
		customerDetailsService.save(form);
		model.addAttribute("title", "Dane zapisano");
		model.addAttribute("message", "Dane klienta zostały zapisane");
		return "message-view";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login-view";
	}
	
//	@PostMapping("/logout")
//	public String showLogoutPage() {
//		return "start-view";
//	}
}
