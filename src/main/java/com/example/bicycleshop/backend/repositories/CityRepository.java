package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	@Query(nativeQuery = true,
			value = "SELECT ci.* FROM CITIES ci JOIN COUNTRIES c ON ci.country_id = c.country_id WHERE c.country_id = :countryId AND ci.name = :cityName")
	Optional<City> getCityByName(@Param("countryId") Long countryId, @Param("cityName") String cityName);
	
	
}
