package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
