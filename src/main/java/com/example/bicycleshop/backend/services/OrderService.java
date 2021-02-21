package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.backend.entities.Order;
import com.example.bicycleshop.backend.entities.enums.OrderStatus;
import com.example.bicycleshop.dtos.CustomerDetailsDto;

import java.util.List;

public interface OrderService {
	
	Order saveNew(CustomerDetailsDto form, String login);
	
	Order updateOrderStatus(Long id, OrderStatus status);
	
	List<Order> getOrders(BusinessEntity businessEntity);
	
	Order getOrder(Long id);
}
