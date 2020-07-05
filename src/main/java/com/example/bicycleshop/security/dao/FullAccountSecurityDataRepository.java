package com.example.bicycleshop.security.dao;

import com.example.bicycleshop.security.model.FullAccountSecurityData;
import com.example.bicycleshop.security.model.FullAccountSecurityDataId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FullAccountSecurityDataRepository extends JpaRepository<FullAccountSecurityData, FullAccountSecurityDataId> {
    List<FullAccountSecurityData> findByUserName(String userName);
}
