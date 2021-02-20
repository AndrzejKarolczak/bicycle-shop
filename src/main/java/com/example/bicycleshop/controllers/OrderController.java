package com.example.bicycleshop.controllers;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.backend.entities.Order;
import com.example.bicycleshop.backend.services.OrderService;
import com.example.bicycleshop.security.AccountDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {
	private final OrderService orderService;
	private final AccountDetailsService accountDetailsService;
	
	public OrderController(OrderService orderService, AccountDetailsService accountDetailsService) {
		this.orderService = orderService;
		this.accountDetailsService = accountDetailsService;
	}
	
	@GetMapping("/orders-list")
	public String showOrders(Model model, Principal principal) {
		BusinessEntity client = accountDetailsService.getBusinessEntityForLogin(principal.getName());
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
	
}
