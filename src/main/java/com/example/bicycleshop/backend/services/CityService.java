package com.example.bicycleshop.backend.services;

import com.example.bicycleshop.backend.entities.City;
import com.example.bicycleshop.backend.entities.Country;
import org.springframework.stereotype.Service;

public interface CityService {
	City getSavedCity(Country country, String cityName);
}
