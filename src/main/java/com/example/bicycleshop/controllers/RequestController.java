package com.example.bicycleshop.controllers;

import com.example.bicycleshop.backend.entities.Bicycle;
import com.example.bicycleshop.backend.entities.BicyclePart;
import com.example.bicycleshop.backend.entities.Order;
import com.example.bicycleshop.backend.entities.enums.OrderStatus;
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
		ProductType product;
		try {
			product = ProductType.valueOf(productType);
		} catch (Exception e) {
			throw new NotFoundException(ProductType.class, productType);
		}
		
		Class<?> productClassName;
		if (product == ProductType.BICYCLE) {
			productClassName = Bicycle.class;
			model.addAttribute("productType", "rowerów");
		} else {
			productClassName = BicyclePart.class;
			model.addAttribute("productType", "części rowerowych");
		}
		model.addAttribute("items", productService.getProductType(productClassName));
		
		return "product-list-view";
	}
	
	@GetMapping("/product-details")
	public String showProductDetailsPage(@RequestParam("id") Long id, Model model) {
		model.addAttribute("product", productService.getProductsById(id));
		return "product-details-view";
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
		Order order = orderService.saveNew(form);
		//tutaj powinien zostać wywołany serwis magazynowy InventoryService
		model.addAttribute("order", order);
		return "payment-details-view";
	}
	
	@PostMapping("payment-successful")
	public String showPaymentSuccessfulPage(@RequestParam("orderId") Long orderId, Model model) {
		// ta metoda to jedynie 'zaślepka', w której powinien zostać wywołany niezależny serwis płatności PaymentService
		orderService.updateOrderStatus(orderId, OrderStatus.PAID);
		model.addAttribute("title", "Transakcja opłacona");
		model.addAttribute("message", "Transakcja o numerze " + orderId + " została opłacona");
		return "message-view";
	}
	
	@RequestMapping("order-cancelled")
	public String showCancelledPage(@RequestParam("orderId") Long orderId, Model model) {
		Long defaultOrderId = 0L;
		model.addAttribute("title", "Transakcja anulowana");
		
		if(!orderId.equals(defaultOrderId)){
			orderService.updateOrderStatus(orderId, OrderStatus.CANCELLED);
			model.addAttribute("message", "Transakcja o numerze " + orderId + " została anulowana");
		} else {
			model.addAttribute("message", "Transakcja została anulowana");
		}

		return "message-view";
	}
	
	@GetMapping("/new-account")
	public String showNewAccountPage(Model model) {
		model.addAttribute("countries", countryService.getHtmlListOfCountries());
		return "new-account-view";
	}
	
	@PostMapping("save-customer-details")
	public String showCustomerDetailsSavedPage(@ModelAttribute("customerDetails") CustomerDetailsDto form, Model model) {
		customerDetailsService.saveNew(form);
		model.addAttribute("title", "Dane zapisano");
		model.addAttribute("message", "Dane klienta zostały zapisane");
		return "message-view";
	}
}
