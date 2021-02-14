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
	public void save(CustomerDetailsDto form) {
		if (form.getPasswordFirst().isEmpty()) {
			processRegistration(form, null);
		} else {
			Account account = getSavedAccount(form.getEmail(), form.getPasswordFirst());
			processRegistration(form, account);
		}
	}
	
	private void processRegistration(CustomerDetailsDto form, Account account) {
		countryRepository
				.findById(form.getBillingCountry())
				.map(c -> {
					Optional<City> cityOrNot = cityRepository.getCityByName(c.getCountryId(), form.getBillingCity());
					
					City city = getSavedCity(c, cityOrNot);
					Address address = getSavedBillingAddress(form.getBillingStreet(), form.getBillingBuildingNumber(),
							form.getBillingSuiteNumber(), form.getBillingPostalCode(), city);
					
					if (Boolean.valueOf(form.getIsIndividual()) == Boolean.TRUE) {
						getSavedIndividual(form.getBillingFirstName(), form.getBillingLastName(), address,
								form.getEmail(), form.getPhone(), account);
					} else {
						getSavedOrganization(form.getBillingCompanyName(), address, form.getEmail(),
								form.getTaxIdNumber(), form.getPhone(), account);
					}
					
					return c;
				})
				.orElseThrow(() -> new NotFoundException(Country.class, form.getBillingCountry()));
	}
	
	private City getSavedCity(Country country, Optional<City> optionalCity) {
		City city;
		if (optionalCity.isPresent())
			city = optionalCity.get();
		else {
			city = new City("Warsaw", country);
			cityRepository.save(city);
		}
		return city;
	}
	
	private Address getSavedBillingAddress(String streetName, String buildingNumber, String suiteNumber, String postalCode, City city) {
		Address address = new BillingAddress(streetName, buildingNumber, suiteNumber, postalCode, city);
		return addressRepository.save(address);
	}
//	private Address getSavedBillingAddress(String streetName, String buildingNumber, String suiteNumber, String postalCode, City city) {
//		Address address = new BillingAddress(streetName, buildingNumber, suiteNumber, postalCode, city);
//		return addressRepository.save(address);
//	}
	
	private BusinessEntity getSavedIndividual(String firstName, String lastName, Address address, String email,
											  String phone, Account account) {
		Individual client;
		if (Objects.isNull(account))
			client = new Individual(firstName, lastName, address, null, email, phone);
		else
			client = new Individual(firstName, lastName, address, null, email, phone, account);
		
		return individualRepository.saveAndFlush(client);
	}
	
	private BusinessEntity getSavedOrganization(String name, Address address, String email, String taxIdNumber,
												String phone, Account account) {
		Organization organization;
		if (Objects.isNull(account))
			organization = new Organization(name, address, null, email, taxIdNumber, phone);
		else
			organization = new Organization(name, address, null, email, taxIdNumber, phone, account);
		
		return organizationRepository.saveAndFlush(organization);
	}
	
	private Account getSavedAccount(String login, String rawPassword) {
		return accountRepository.findByLogin(login)
				.orElseGet(() ->
						authorityGroupRepository.findById(Roles.CLIENT.getAuthorityGroupNumber())
								.map(group -> {
									Account account = new Account(login, passwordEncoder.encode(rawPassword), group);
									account.setEnabled(true)
											.setCredentialsNonExpired(true)
											.setAccountNonExpired(true)
											.setAccountNonLocked(true);
									return accountRepository.save(account);
								})
								.orElseThrow(() -> new AccountAlreadyExists(login)));
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
