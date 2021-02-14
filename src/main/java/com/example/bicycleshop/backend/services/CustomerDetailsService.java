package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.dtos.CustomerDetailsDto;

public interface CustomerDetailsService {
	BusinessEntity save(CustomerDetailsDto form);
	
	//void update(CustomerDetailsDto form);
}
