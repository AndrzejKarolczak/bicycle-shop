package com.example.bicycleshop.backend.dao;

import com.example.bicycleshop.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
