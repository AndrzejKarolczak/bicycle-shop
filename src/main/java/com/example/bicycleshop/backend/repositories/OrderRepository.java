package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT o FROM Order o JOIN FETCH o.productsInOrder pid JOIN FETCH pid.product WHERE o.orderId = :id")
	Optional<Order> getOrderDetails(@Param("id") Long id);
}
