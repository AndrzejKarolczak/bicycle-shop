package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.*;
import com.example.bicycleshop.backend.repositories.*;
import com.example.bicycleshop.dtos.CustomerDetailsDto;
import com.example.bicycleshop.exceptions.AccountAlreadyExists;
import com.example.bicycleshop.exceptions.NotFoundException;
import com.example.bicycleshop.security.Roles;
import com.example.bicycleshop.security.entities.Account;
import com.example.bicycleshop.security.repositories.AccountRepository;
import com.example.bicycleshop.security.repositories.AuthorityGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {
	private final CountryRepository countryRepository;
	private final CityRepository cityRepository;
	private final AddressRepository addressRepository;
	private final IndividualRepository individualRepository;
	private final OrganizationRepository organizationRepository;
	private final AccountRepository accountRepository;
	private final AuthorityGroupRepository authorityGroupRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomerDetailsServiceImpl(CountryRepository countryRepository, CityRepository cityRepository,
									  AddressRepository addressRepository, IndividualRepository individualRepository,
									  OrganizationRepository organizationRepository, AccountRepository accountRepository, AuthorityGroupRepository authorityGroupRepository, PasswordEncoder passwordEncoder) {
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
		this.addressRepository = addressRepository;
		this.individualRepository = individualRepository;
		this.organizationRepository = organizationRepository;
		this.accountRepository = accountRepository;
		this.authorityGroupRepository = authorityGroupRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public BusinessEntity save(CustomerDetailsDto form) {
		if (Objects.isNull(form.getPasswordFirst())) {
			return processRegistration(form, null);
		} else {
			Account account = getSavedAccount(form.getEmail(), form.getPasswordFirst());
			return processRegistration(form, account);
		}
	}
	
	private BusinessEntity processRegistration(CustomerDetailsDto form, Account account) {
		return countryRepository
			.findById(form.getBillingCountry())
			.map(country -> {
				Optional<City> cityOrNot = cityRepository.getCityByName(country.getCountryId(),
					form.getBillingCity().trim().toUpperCase());
				
				City city = getSavedCity(country, cityOrNot);
				Address billingAddress = getSavedAddress(form.getBillingStreet(), form.getBillingBuildingNumber(),
					form.getBillingSuiteNumber(), form.getBillingPostalCode(), city);
				
				Address shippingAddress;
				if (hasShippingAddress(form)) {
					shippingAddress = getShippingAddress(form);
				} else {
					shippingAddress = billingAddress;
				}
				
				BusinessEntity businessEntity;
				if (Boolean.valueOf(form.getIsIndividual()) == Boolean.TRUE) {
					businessEntity = getSavedIndividual(form.getBillingFirstName(), form.getBillingLastName(),
						billingAddress, shippingAddress, form.getEmail(), form.getPhone(), account);
				} else {
					businessEntity = getSavedOrganization(form.getBillingCompanyName(), billingAddress,
						shippingAddress, form.getEmail(), form.getTaxIdNumber(), form.getPhone(), account);
				}
				
				return businessEntity;
			}).orElseThrow(() -> new NotFoundException(Country.class, form.getBillingCountry()));
	}
	
	private boolean hasShippingAddress(CustomerDetailsDto form) {
		return !form.getShippingStreet().isEmpty() & !form.getShippingBuildingNumber().isEmpty() &
			!form.getShippingPostalCode().isEmpty() & !form.getShippingCity().isEmpty();
	}
	
	private City getSavedCity(Country country, Optional<City> optionalCity) {
		City city;
		city = optionalCity.orElseGet(() -> cityRepository.save(new City("Warsaw", country)));
		return city;
	}
	
	private Address getSavedAddress(String streetName, String buildingNumber, String suiteNumber, String postalCode, City city) {
		return addressRepository.save(new Address(streetName, buildingNumber, suiteNumber, postalCode, city));
	}
	
	private Address getShippingAddress(CustomerDetailsDto form) {
		return countryRepository
			.findById(form.getShippingCountry())
			.map(country -> {
				Optional<City> cityOrNot = cityRepository.getCityByName(country.getCountryId(),
					form.getShippingCity().trim().toUpperCase());
				
				City city = getSavedCity(country, cityOrNot);
				return getSavedAddress(form.getShippingStreet(), form.getShippingBuildingNumber(),
					form.getShippingSuiteNumber(), form.getShippingPostalCode(), city);
			})
			.orElseThrow(() -> new NotFoundException(Country.class, form.getBillingCountry()));
	}
	
	private BusinessEntity getSavedIndividual(String firstName, String lastName, Address billingAddress,
											  Address shippingAddress, String email, String phone, Account account) {
		Individual client;
		if (Objects.isNull(account))
			client = new Individual(firstName, lastName, billingAddress, shippingAddress, email, phone);
		else
			client = new Individual(firstName, lastName, billingAddress, shippingAddress, email, phone, account);
		
		return individualRepository.saveAndFlush(client);
	}
	
	private BusinessEntity getSavedOrganization(String name, Address billingAddress, Address shippingAddress, String email,
												String taxIdNumber, String phone, Account account) {
		Organization organization;
		if (Objects.isNull(account))
			organization = new Organization(name, billingAddress, shippingAddress, email, taxIdNumber, phone);
		else
			organization = new Organization(name, billingAddress, shippingAddress, email, taxIdNumber, phone, account);
		
		return organizationRepository.saveAndFlush(organization);
	}
	
	private Account getSavedAccount(String login, String rawPassword) {
		return accountRepository.findByLogin(login)
			.orElseGet(() -> authorityGroupRepository
				.findById(Roles.CLIENT.getAuthorityGroupNumber())
				.map(group -> {
					Account account = new Account(login, passwordEncoder.encode(rawPassword), group);
					account.setEnabled(true)
						.setCredentialsNonExpired(true)
						.setAccountNonExpired(true)
						.setAccountNonLocked(true);
					return accountRepository.save(account);
				}).orElseThrow(() -> new AccountAlreadyExists(login)));
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
