package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.ProductInOrder;
import com.example.bicycleshop.backend.entities.ProductInOrderPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, ProductInOrderPrimaryKey> {

}
