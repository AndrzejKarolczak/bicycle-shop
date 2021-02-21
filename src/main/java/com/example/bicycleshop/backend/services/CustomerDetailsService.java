package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.dtos.CustomerDetailsDto;

public interface CustomerDetailsService {
	BusinessEntity saveNew(CustomerDetailsDto form);
	
	CustomerDetailsDto getCustomerDetailsForLogin(String login);
	
	//void update(CustomerDetailsDto form);
}
