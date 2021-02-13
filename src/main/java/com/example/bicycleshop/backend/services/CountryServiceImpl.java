package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.dtos.CountryDto;
import com.example.bicycleshop.backend.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CountryServiceImpl implements CountryService {
	
	private final CountryRepository countryRepository;
	
	@Autowired
	public CountryServiceImpl(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	@Override
	public List<CountryDto> getCountries() {
		return countryRepository.findAll().stream().map(CountryDto::new).collect(Collectors.toList());
	}
}
