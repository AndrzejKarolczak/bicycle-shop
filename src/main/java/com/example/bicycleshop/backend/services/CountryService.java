package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.dtos.CountryDto;

import java.util.List;

public interface CountryService {
	List<CountryDto> getCountries();
	
	String getHtmlListOfCountries();
	
	String getHtmlListOfCountries(Long billingCountry);
}
