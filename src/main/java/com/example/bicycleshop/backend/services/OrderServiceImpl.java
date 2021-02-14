package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.backend.entities.Order;
import com.example.bicycleshop.backend.entities.Product;
import com.example.bicycleshop.backend.entities.ProductInOrder;
import com.example.bicycleshop.backend.entities.enums.OrderStatus;
import com.example.bicycleshop.backend.repositories.OrderRepository;
import com.example.bicycleshop.backend.repositories.ProductInOrderRepository;
import com.example.bicycleshop.backend.repositories.ProductRepository;
import com.example.bicycleshop.dtos.BasketItem;
import com.example.bicycleshop.dtos.CustomerDetailsDto;
import com.example.bicycleshop.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	private final CustomerDetailsService customerDetailsService;
	private final OrderRepository orderRepository;
	private final ProductInOrderRepository productInOrderRepository;
	private final ProductRepository productRepository;
	
	public OrderServiceImpl(CustomerDetailsService customerDetailsService, OrderRepository orderRepository,
							ProductInOrderRepository productInOrderRepository, ProductRepository productRepository) {
		this.customerDetailsService = customerDetailsService;
		this.orderRepository = orderRepository;
		this.productInOrderRepository = productInOrderRepository;
		this.productRepository = productRepository;
	}
	
	@Override
	public Order save(CustomerDetailsDto form) {
		BusinessEntity client = customerDetailsService.save(form);
		Order order = orderRepository.saveAndFlush(new Order(client, OrderStatus.PRELIMINARY));
		List<BasketItem> basket = prepareBasket(form);
		
		basket.forEach(item ->
				productRepository.findById(item.getId()).map(product -> {
					ProductInOrder partInOrder = new ProductInOrder(product, order, item.getQuantity(), item.getPrice());
					return productInOrderRepository.save(partInOrder);
				}).orElseThrow(() -> new NotFoundException(Product.class, item.getId()))
		);
		
		return order;
	}
	
	private static List<BasketItem> prepareBasket(CustomerDetailsDto form) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			BasketItem[] basketItems = mapper.readValue(form.getBasketContents(), BasketItem[].class);
			return Arrays.asList(basketItems);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
