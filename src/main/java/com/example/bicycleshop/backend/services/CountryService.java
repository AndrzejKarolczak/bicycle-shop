package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.dtos.CountryDto;

import java.util.List;

public interface CountryService {
	List<CountryDto> getCountries();
}
