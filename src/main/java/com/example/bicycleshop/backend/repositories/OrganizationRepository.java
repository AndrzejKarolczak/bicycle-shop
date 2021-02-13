package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
