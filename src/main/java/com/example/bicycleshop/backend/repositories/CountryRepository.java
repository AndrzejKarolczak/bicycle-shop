package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
