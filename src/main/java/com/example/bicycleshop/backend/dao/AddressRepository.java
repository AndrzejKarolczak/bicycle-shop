package com.example.bicycleshop.backend.dao;

import com.example.bicycleshop.backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
