package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.*;
import com.example.bicycleshop.backend.repositories.*;
import com.example.bicycleshop.dtos.CustomerDetailsDto;
import com.example.bicycleshop.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {
	private final CountryRepository countryRepository;
	private final CityRepository cityRepository;
	private final AddressRepository addressRepository;
	private final IndividualRepository individualRepository;
	private final OrganizationRepository organizationRepository;
	
	@Autowired
	public CustomerDetailsServiceImpl(CountryRepository countryRepository, CityRepository cityRepository,
									  AddressRepository addressRepository, IndividualRepository individualRepository,
									  OrganizationRepository organizationRepository) {
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
		this.addressRepository = addressRepository;
		this.individualRepository = individualRepository;
		this.organizationRepository = organizationRepository;
	}
	
	@Override
	public void save(CustomerDetailsDto form) {
		if (form.getPasswordFirst().isEmpty()) {
			countryRepository
					.findById(form.getBillingCountry())
					.map(c -> {
						Optional<City> cityOrNot = cityRepository.getCityByName(c.getCountryId(), form.getBillingCity());
						
						City city = getSavedCity(c, cityOrNot);
						Address address = getSavedAddress(form.getBillingStreet(), form.getBillingBuildingNumber(),
								form.getBillingSuiteNumber(), form.getBillingPostalCode(), city);
						
						if (Boolean.valueOf(form.getIsIndividual()) == Boolean.TRUE) {
							getSavedIndividual(form.getBillingFirstName(), form.getBillingLastName(), address,
									form.getEmail(), form.getPhone());
						} else {
							getSavedOrganization(form.getBillingCompanyName(), address, form.getEmail(),
									form.getTaxIdNumber(), form.getPhone());
						}
						
						return c;
					})
					.orElseThrow(() -> new NotFoundException(Country.class, form.getBillingCountry()));
		} else {
			
			//new account
		}
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
	
	private Address getSavedAddress(String streetName, String buildingNumber, String suiteNumber, String postalCode, City city) {
		Address address = new Address(streetName, buildingNumber, suiteNumber, postalCode, city);
		return addressRepository.save(address);
	}
	
	private BusinessEntity getSavedIndividual(String firstName, String lastName, Address address, String email, String phone) {
		Individual client = new Individual(firstName, lastName, address, email, phone);
		return individualRepository.saveAndFlush(client);
	}
	
	private BusinessEntity getSavedOrganization(String name, Address address, String email, String taxIdNumber, String phone) {
		Organization organization = new Organization(name, address, email, taxIdNumber, phone);
		return organizationRepository.saveAndFlush(organization);
	}
}
