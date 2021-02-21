package com.example.bicycleshop.controllers;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.backend.entities.Order;
import com.example.bicycleshop.backend.services.CountryService;
import com.example.bicycleshop.backend.services.CustomerDetailsService;
import com.example.bicycleshop.backend.services.OrderService;
import com.example.bicycleshop.dtos.CustomerDetailsDto;
import com.example.bicycleshop.security.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class AuthorizedRequestController {
	private final OrderService orderService;
	private final AuthorizationService authorizationService;
	private final CountryService countryService;
	private final CustomerDetailsService customerDetailsService;
	
	public AuthorizedRequestController(OrderService orderService, AuthorizationService authorizationService,
									   CountryService countryService, CustomerDetailsService customerDetailsService) {
		this.orderService = orderService;
		this.authorizationService = authorizationService;
		this.countryService = countryService;
		this.customerDetailsService = customerDetailsService;
	}
	
	@GetMapping("/orders-list")
	public String showOrders(Model model, Principal principal) {
		BusinessEntity client = authorizationService.getBusinessEntityForLogin(principal.getName());
		List<Order> orders = orderService.getOrders(client);
		model.addAttribute("orders", orders);
		return "orders-list-view";
	}
	
	@GetMapping("/order-details")
	public String showOrderDetails(@RequestParam("id") Long id, Model model) {
		Order order = orderService.getOrder(id);
		
		model.addAttribute("orderDetails", order);
		return "products-in-order-list-view";
	}
	
	@RequestMapping("/saved-customer-details")
	public String showSavedCustomerDetailsPage(Model model, Principal principal) {
		CustomerDetailsDto client = customerDetailsService.getCustomerDetailsForLogin(principal.getName());
		model.addAttribute("customerDetails", client);
		model.addAttribute("billingCountries", countryService.getHtmlListOfCountries(client.getBillingCountry()));
		model.addAttribute("shippingCountries", countryService.getHtmlListOfCountries(client.getShippingCountry()));
		if (Boolean.parseBoolean(client.getIsIndividual())) {
			return "saved-individual-details-view";
		} else {
			throw new UnsupportedOperationException("Not implemented yet");
		}
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "access-denied-view";
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
