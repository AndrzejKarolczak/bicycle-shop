package com.example.bicycleshop.security.repositories;

import com.example.bicycleshop.security.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findAuthorityByName(String name);

    static Set<Authority> convertRawRecordToSet(Object[][] rawRecord){
        Set<Authority> resultSet = new HashSet<>();

        for (Object[] objects : rawRecord) {
            resultSet.add(new Authority((BigInteger) objects[0], (String) objects[1]));
        }

        return resultSet;
    }
}
