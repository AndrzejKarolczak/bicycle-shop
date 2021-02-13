package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
