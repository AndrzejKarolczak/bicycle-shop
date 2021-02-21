package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.Address;
import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.security.entities.Account;

public interface BusinessEntityService {
	BusinessEntity getSavedIndividual(String firstName, String lastName, Address billingAddress,
									  Address shippingAddress, String email, String phone, Account account);
	
	BusinessEntity getSavedOrganization(String name, Address billingAddress, Address shippingAddress, String email,
										String taxIdNumber, String phone, Account account);
}
