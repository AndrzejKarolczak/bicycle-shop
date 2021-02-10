package com.example.bicycleshop.backend.repository;

import com.example.bicycleshop.backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
