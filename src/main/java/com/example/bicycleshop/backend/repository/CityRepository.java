package com.example.bicycleshop.backend.repository;

import com.example.bicycleshop.backend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
