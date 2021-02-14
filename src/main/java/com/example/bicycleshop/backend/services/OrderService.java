package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.Order;
import com.example.bicycleshop.dtos.CustomerDetailsDto;

public interface OrderService {
	Order save(CustomerDetailsDto form);
}
