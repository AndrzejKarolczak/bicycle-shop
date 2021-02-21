package com.example.bicycleshop.backend.services.implementations;

import com.example.bicycleshop.backend.entities.City;
import com.example.bicycleshop.backend.entities.Country;
import com.example.bicycleshop.backend.repositories.CityRepository;
import com.example.bicycleshop.backend.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
class CityServiceImpl implements CityService {
	
	private final CityRepository cityRepository;
	
	@Autowired
	CityServiceImpl(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}
	
	@Override
	public City getSavedCity(Country country, String cityName) {
		Optional<City> cityOrNot = cityRepository.getCityByName(country.getCountryId(), cityName);
		return cityOrNot.orElseGet(() -> cityRepository.save(new City(cityName, country)));
	}
}
