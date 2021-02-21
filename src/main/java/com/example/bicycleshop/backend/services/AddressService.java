package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.Address;
import com.example.bicycleshop.backend.entities.City;

public interface AddressService {
	Address getSavedAddress(String streetName, String buildingNumber, String suiteNumber, String postalCode, City city);
}
