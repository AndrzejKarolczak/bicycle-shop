package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
