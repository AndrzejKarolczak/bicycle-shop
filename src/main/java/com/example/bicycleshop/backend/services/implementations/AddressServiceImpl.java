package com.example.bicycleshop.backend.services.implementations;

import com.example.bicycleshop.backend.entities.Address;
import com.example.bicycleshop.backend.entities.City;
import com.example.bicycleshop.backend.repositories.AddressRepository;
import com.example.bicycleshop.backend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class AddressServiceImpl implements AddressService {
	private final AddressRepository addressRepository;
	
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	@Override
	public Address getSavedAddress(String streetName, String buildingNumber, String suiteNumber, String postalCode, City city) {
		return addressRepository.save(new Address(streetName, buildingNumber, suiteNumber, postalCode, city));
	}
	
}
