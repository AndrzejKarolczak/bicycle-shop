package com.example.bicycleshop.backend.services.implementations;

import com.example.bicycleshop.backend.entities.Address;
import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.backend.entities.Individual;
import com.example.bicycleshop.backend.entities.Organization;
import com.example.bicycleshop.backend.repositories.IndividualRepository;
import com.example.bicycleshop.backend.repositories.OrganizationRepository;
import com.example.bicycleshop.backend.services.BusinessEntityService;
import com.example.bicycleshop.security.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
class BusinessEntityServiceImpl implements BusinessEntityService {
	private final IndividualRepository individualRepository;
	private final OrganizationRepository organizationRepository;
	
	@Autowired
	BusinessEntityServiceImpl(IndividualRepository individualRepository, OrganizationRepository organizationRepository) {
		this.individualRepository = individualRepository;
		this.organizationRepository = organizationRepository;
	}
	
	@Override
	public BusinessEntity getSavedIndividual(String firstName, String lastName, Address billingAddress,
											 Address shippingAddress, String email, String phone, Account account) {
		Individual client;
		if (Objects.isNull(account))
			client = new Individual(firstName, lastName, billingAddress, shippingAddress, email, phone);
		else
			client = new Individual(firstName, lastName, billingAddress, shippingAddress, email, phone, account);
		
		return individualRepository.saveAndFlush(client);
	}
	
	@Override
	public BusinessEntity getSavedOrganization(String name, Address billingAddress, Address shippingAddress, String email,
											   String taxIdNumber, String phone, Account account) {
		Organization organization;
		if (Objects.isNull(account))
			organization = new Organization(name, billingAddress, shippingAddress, email, taxIdNumber, phone);
		else
			organization = new Organization(name, billingAddress, shippingAddress, email, taxIdNumber, phone, account);
		
		return organizationRepository.saveAndFlush(organization);
	}
}
