package com.example.bicycleshop.backend.services.implementations;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.backend.entities.Order;
import com.example.bicycleshop.backend.entities.Product;
import com.example.bicycleshop.backend.entities.ProductInOrder;
import com.example.bicycleshop.backend.entities.enums.OrderStatus;
import com.example.bicycleshop.backend.repositories.BusinessEntityRepository;
import com.example.bicycleshop.backend.repositories.OrderRepository;
import com.example.bicycleshop.backend.repositories.ProductInOrderRepository;
import com.example.bicycleshop.backend.repositories.ProductRepository;
import com.example.bicycleshop.backend.services.CustomerDetailsService;
import com.example.bicycleshop.backend.services.OrderService;
import com.example.bicycleshop.dtos.BasketItem;
import com.example.bicycleshop.dtos.CustomerDetailsDto;
import com.example.bicycleshop.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
class OrderServiceImpl implements OrderService {
	private final CustomerDetailsService customerDetailsService;
	private final OrderRepository orderRepository;
	private final ProductInOrderRepository productInOrderRepository;
	private final ProductRepository productRepository;
	private final BusinessEntityRepository businessEntityRepository;
	
	public OrderServiceImpl(CustomerDetailsService customerDetailsService, OrderRepository orderRepository,
							ProductInOrderRepository productInOrderRepository, ProductRepository productRepository, BusinessEntityRepository businessEntityRepository) {
		this.customerDetailsService = customerDetailsService;
		this.orderRepository = orderRepository;
		this.productInOrderRepository = productInOrderRepository;
		this.productRepository = productRepository;
		this.businessEntityRepository = businessEntityRepository;
	}
	
	@Override
	public Order saveNew(CustomerDetailsDto form) {
		BusinessEntity client = customerDetailsService.saveNew(form);
		Order order = new Order(client, OrderStatus.NEW);
		List<BasketItem> basket = prepareBasket(form);
		
		basket.forEach(item ->
			productRepository.findById(item.getId())
				.map(product -> {
					ProductInOrder partInOrder = new ProductInOrder(product, order, item.getQuantity(), item.getPrice());
					order.addProductToOrder(partInOrder);
					return productInOrderRepository.save(partInOrder);
				})
				.orElseThrow(() -> new NotFoundException(Product.class, item.getId()))
		);

		return orderRepository.saveAndFlush(order);
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
	
	@Override
	public Order updateOrderStatus(Long id, OrderStatus status) {
		return orderRepository.findById(id)
			.map(order -> {
				order.setOrderStatus(status);
				return orderRepository.saveAndFlush(order);
			})
			.orElseThrow(() -> new NotFoundException(Order.class, id));
	}
	
	@Override
	public List<Order> getOrders(BusinessEntity businessEntity) {
		return businessEntityRepository.getOrderByClientId(businessEntity.getBusinessEntityId())
			.map(BusinessEntity::getOrders)
			.orElseThrow(() -> new NotFoundException(BusinessEntity.class, businessEntity.getBusinessEntityId()));
	}
	
	@Override
	public Order getOrder(Long id) {
		return orderRepository.getOrderDetails(id)
			.orElseThrow(() -> new NotFoundException(Order.class, id));
	}
}
