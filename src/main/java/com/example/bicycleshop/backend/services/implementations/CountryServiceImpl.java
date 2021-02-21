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
	public static final Long DEFAULT_COUNTRY_ID = 136L;
	
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
		return getHtmlListOfCountries(DEFAULT_COUNTRY_ID);
	}
	
	@Override
	public String getHtmlListOfCountries(Long countryId) {
		String htmlOption = "<option value=\"%1$s\" %2$s>%3$s</option>\n";
		StringBuilder builder = new StringBuilder();
		
		countryRepository.findAll().forEach(country ->
			builder.append(String.format(htmlOption,
				country.getCountryId(),
				country.getCountryId().equals(countryId) ? "selected" : "",
				country.getName()
			))
		);
		
		return builder.toString();
	}
}
