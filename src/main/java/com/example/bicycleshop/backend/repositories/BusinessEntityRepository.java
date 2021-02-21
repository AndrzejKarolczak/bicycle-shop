package com.example.bicycleshop.backend.repositories;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessEntityRepository extends JpaRepository<BusinessEntity, Long> {
	@Query("SELECT b FROM BusinessEntity b LEFT JOIN FETCH b.orders o WHERE b.businessEntityId = :businessEntityId")
	Optional<BusinessEntity> getOrderByClientId(@Param("businessEntityId") Long businessEntityId);
	
	@Query("SELECT b FROM BusinessEntity b JOIN FETCH b.billingAddress ba JOIN FETCH b.shippingAddress sa " +
		"WHERE b.businessEntityId = :id")
	Optional<BusinessEntity> getBusinessEntityDetailsById(@Param("id") Long id);
}
