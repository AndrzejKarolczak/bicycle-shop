package com.example.bicycleshop.backend.dao;

import com.example.bicycleshop.backend.model.ProductInOrder;
import com.example.bicycleshop.backend.model.ProductInOrderPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, ProductInOrderPrimaryKey> {

}
