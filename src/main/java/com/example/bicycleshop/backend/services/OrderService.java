package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.backend.entities.Order;
import com.example.bicycleshop.dtos.CustomerDetailsDto;

import java.util.List;

public interface OrderService {
	Order save(CustomerDetailsDto form);
	
	List<Order> getOrders(BusinessEntity businessEntity);
	
	Order getOrder(Long id);
}
