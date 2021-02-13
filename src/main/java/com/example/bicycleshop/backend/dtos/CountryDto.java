package com.example.bicycleshop.backend.dtos;

import com.example.bicycleshop.backend.entities.Country;
import lombok.Getter;

@Getter
public class CountryDto {
	private final long id;
	private final String name;
	
	public CountryDto(Country country) {
		id = country.getCountryId();
		name = country.getName();
	}
}
