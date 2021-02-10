package com.example.bicycleshop.backend.repository;

import com.example.bicycleshop.backend.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
