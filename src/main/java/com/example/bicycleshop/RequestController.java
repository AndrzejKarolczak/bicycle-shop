package com.example.bicycleshop;

import com.example.bicycleshop.backend.entities.enums.ProductType;
import com.example.bicycleshop.backend.services.CountryService;
import com.example.bicycleshop.backend.services.ProductService;
import com.example.bicycleshop.forms.CustomerDetailsForm;
import com.example.bicycleshop.forms.PaymentDetailsForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
		return "start-view";
	}
	
	@GetMapping("/products")
	public String showProductsPage(@RequestParam("productType") String productType, Model model) {
		ProductType product = ProductType.valueOf(productType);
		if (product == ProductType.BICYCLE) {
			model.addAttribute("productType", "rowerów");
		} else if (product == ProductType.PART) {
			model.addAttribute("productType", "części rowerowych");
		} else {
			throw new IllegalArgumentException("1");
		}
		model.addAttribute("items", productService.getProductType(productType));
		
		return "product-list-view";
	}
	
	@GetMapping({"/basket"})
	public String showBasketContentsPage() {
		return "basket-contents-view";
	}
	
	@GetMapping("/customer-details")
	public String showCustomerDetailsPage(Model model) {
		String sessionId = UUID.randomUUID().toString();
		model.addAttribute("countries", countryService.getCountries());
		model.addAttribute("customerDetails", new CustomerDetailsForm(sessionId));
		return "customer-details-view";
	}
	
	@PostMapping("/payment-details")
	public String showPaymentDetailsPage(@ModelAttribute("customerDetails") CustomerDetailsForm form, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			BasketItem[] basketItems = mapper.readValue(form.getBasketContents(), BasketItem[].class);
			List<BasketItem> basket = Arrays.asList(basketItems);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("session", form.getSessionId());
		model.addAttribute("paymentDetails", new PaymentDetailsForm()); //TODO
		return "payment-details-view";
	}
	
	@PostMapping("payment-successful")
	public String showPaymentSuccessfulPage(@ModelAttribute("paymentDetails") PaymentDetailsForm form){
		
		return "payment-successful-view";
	}
	
	@RequestMapping("order-cancelled")
	public String showCancelledPage(@RequestParam("session") String sessionId){
		
		return "order-cancelled-view";
	}
	
	@GetMapping("/saved-customer-details")
	public String showSavedCustomerDetailsPage() {
		return "saved-customer-details-view";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "access-denied-view";
	}
	
	@GetMapping("/new-account")
	public String showNewAccountPage() {
		return "new-account-view";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login-view";
	}
}
