package com.example.bicycleshop.backend.services.implementations;

import com.example.bicycleshop.backend.services.CountryService;
import com.example.bicycleshop.dtos.CountryDto;
import com.example.bicycleshop.backend.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
class CountryServiceImpl implements CountryService {
	
	private final CountryRepository countryRepository;
	public static final String DEFAULT_COUNTRY = "Polska";
	
	@Autowired
	public CountryServiceImpl(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	@Override
	public List<CountryDto> getCountries() {
		return countryRepository.findAll().stream().map(CountryDto::new).collect(Collectors.toList());
	}
	
	@Override
	public String getHtmlListOfCountries() {
		String htmlOption = "<option value=\"%1$s\" %2$s>%3$s</option>\n";
		StringBuilder builder = new StringBuilder();
		
		countryRepository.findAll().forEach(country ->
			builder.append(String.format(htmlOption,
				country.getCountryId(),
				country.getName().equals(DEFAULT_COUNTRY) ? "selected" : "",
				country.getName()
			))
		);
		
		return builder.toString();
	}
}
