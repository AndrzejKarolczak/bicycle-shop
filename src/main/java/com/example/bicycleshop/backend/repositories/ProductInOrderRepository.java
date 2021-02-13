package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.ProductInOrder;
import com.example.bicycleshop.backend.entities.ProductInOrderPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, ProductInOrderPrimaryKey> {

}
