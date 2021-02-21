package com.example.bicycleshop.backend.services.implementations;

import com.example.bicycleshop.backend.entities.Address;
import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.backend.entities.City;
import com.example.bicycleshop.backend.entities.Country;
import com.example.bicycleshop.backend.repositories.CountryRepository;
import com.example.bicycleshop.backend.services.AddressService;
import com.example.bicycleshop.backend.services.BusinessEntityService;
import com.example.bicycleshop.backend.services.CityService;
import com.example.bicycleshop.backend.services.CustomerDetailsService;
import com.example.bicycleshop.dtos.CustomerDetailsDto;
import com.example.bicycleshop.exceptions.NotFoundException;
import com.example.bicycleshop.security.AccountDetailsService;
import com.example.bicycleshop.security.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
class CustomerDetailsServiceImpl implements CustomerDetailsService {
	private final CountryRepository countryRepository;
	private final CityService cityService;
	private final AddressService addressService;
	private final AccountDetailsService accountDetailsService;
	private final BusinessEntityService businessEntityService;
	
	
	@Autowired
	public CustomerDetailsServiceImpl(CountryRepository countryRepository, CityService cityService,
									  AddressService addressService, AccountDetailsService accountDetailsService, BusinessEntityService businessEntityService) {
		this.countryRepository = countryRepository;
		this.cityService = cityService;
		this.addressService = addressService;
		this.accountDetailsService = accountDetailsService;
		this.businessEntityService = businessEntityService;
	}
	
	@Override
	public BusinessEntity save(CustomerDetailsDto form) {
		if (Objects.isNull(form.getPasswordFirst())) {
			return processRegistration(form, null);
		} else {
			Account account = accountDetailsService.getSavedAccount(form.getEmail(), form.getPasswordFirst());
			return processRegistration(form, account);
		}
	}
	
	private BusinessEntity processRegistration(CustomerDetailsDto form, Account account) {
		return countryRepository
			.findById(form.getBillingCountry())
			.map(country -> {
				City city = cityService.getSavedCity(country, form.getBillingCity().trim().toUpperCase());
				
				Address billingAddress = addressService.getSavedAddress(form.getBillingStreet(),
					form.getBillingBuildingNumber(), form.getBillingSuiteNumber(), form.getBillingPostalCode(), city);
				
				Address shippingAddress = getShippingAddress(form, billingAddress);
				
				return getSavedBusinessEntity(form, account, billingAddress, shippingAddress);
			})
			.orElseThrow(() -> new NotFoundException(Country.class, form.getBillingCountry()));
	}
	
	private Address getShippingAddress(CustomerDetailsDto form, Address billingAddress) {
		if (form.hasShippingAddress()) {
			return getSavedShippingAddress(form);
		} else {
			return billingAddress;
		}
	}
	
	private Address getSavedShippingAddress(CustomerDetailsDto form) {
		return countryRepository
			.findById(form.getShippingCountry())
			.map(country -> {
				City city = cityService.getSavedCity(country, form.getShippingCity().trim().toUpperCase());
				return addressService.getSavedAddress(form.getShippingStreet(), form.getShippingBuildingNumber(),
					form.getShippingSuiteNumber(), form.getShippingPostalCode(), city);
			})
			.orElseThrow(() -> new NotFoundException(Country.class, form.getBillingCountry()));
	}
	
	private BusinessEntity getSavedBusinessEntity(CustomerDetailsDto form, Account account, Address billingAddress,
												  Address shippingAddress) {
		if (Boolean.valueOf(form.getIsIndividual()) == Boolean.TRUE) {
			return businessEntityService.getSavedIndividual(form.getBillingFirstName(),
				form.getBillingLastName(), billingAddress, shippingAddress, form.getEmail(), form.getPhone(), account);
		} else {
			return businessEntityService.getSavedOrganization(form.getBillingCompanyName(),
				billingAddress, shippingAddress, form.getEmail(), form.getTaxIdNumber(), form.getPhone(), account);
		}
	}

//	@Override
//	public void update(CustomerDetailsDto form) {
//		if (form.getPasswordFirst().isEmpty()) {
//			processUpdate(form, null);
//		} else {
//			Account account = getSavedAccount(form.getEmail(), form.getPasswordFirst());
//			processUpdate(form, account);
//		}
//	}
//
//	private void processUpdate(CustomerDetailsDto form, Account account){
//		countryRepository
//				.findById(form.getBillingCountry())
//				.map(c -> {
//					Optional<City> cityOrNot = cityRepository.getCityByName(c.getCountryId(), form.getBillingCity());
//
//					City city = getSavedCity(c, cityOrNot);
//					Address address = getSavedAddress(form.getBillingStreet(), form.getBillingBuildingNumber(),
//							form.getBillingSuiteNumber(), form.getBillingPostalCode(), city);
//
//					if (Boolean.valueOf(form.getIsIndividual()) == Boolean.TRUE) {
//						getSavedIndividual(form.getBillingFirstName(), form.getBillingLastName(), address,
//								form.getEmail(), form.getPhone(), account);
//					} else {
//						getSavedOrganization(form.getBillingCompanyName(), address, form.getEmail(),
//								form.getTaxIdNumber(), form.getPhone(), account);
//					}
//
//					return c;
//				})
//				.orElseThrow(() -> new NotFoundException(Country.class, form.getBillingCountry()));
//	}

}
