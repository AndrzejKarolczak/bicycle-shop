package com.example.bicycleshop.backend.repository;

import com.example.bicycleshop.backend.model.ProductInOrder;
import com.example.bicycleshop.backend.model.ProductInOrderPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, ProductInOrderPrimaryKey> {

}
