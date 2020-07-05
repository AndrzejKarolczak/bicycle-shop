package com.example.bicycleshop.backend.dao;

import com.example.bicycleshop.backend.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
