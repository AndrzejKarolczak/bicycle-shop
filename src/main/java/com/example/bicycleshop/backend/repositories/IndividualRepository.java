package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<Individual, Long> {
}
