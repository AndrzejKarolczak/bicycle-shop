package com.example.bicycleshop.security.repositories;

import com.example.bicycleshop.security.entities.FullAccountSecurityData;
import com.example.bicycleshop.security.entities.FullAccountSecurityDataId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FullAccountSecurityDataRepository extends JpaRepository<FullAccountSecurityData, FullAccountSecurityDataId> {
    List<FullAccountSecurityData> findByUserName(String userName);
}
