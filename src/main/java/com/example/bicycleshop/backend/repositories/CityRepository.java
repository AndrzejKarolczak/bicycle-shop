package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	@Query("SELECT ci FROM City ci JOIN ci.country c WHERE c.countryId = :countryId AND ci.name = :cityName")
	Optional<City> getCityByName(@Param("countryId") Long countryId, @Param("cityName") String cityName);
}
