package com.example.bicycleshop.backend.dao;

import com.example.bicycleshop.backend.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<Individual, Long> {
}
