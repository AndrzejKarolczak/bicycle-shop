package com.example.bicycleshop.security.dao;

import com.example.bicycleshop.security.model.Authority;
import com.example.bicycleshop.security.model.AuthorityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public interface AuthorityGroupRepository extends JpaRepository<AuthorityGroup, Long> {
    Optional<AuthorityGroup> findAuthorityGroupByName(String Name);

    @Query(nativeQuery = true,
        value = "SELECT * FROM authority_groups ag " +
            "JOIN authority_in_group aig ON aig.group_id = ag.group_id " +
            "JOIN authorities a ON a.authority_id = aig.authority_id " +
            "WHERE ag.group_name = :groupName")
    Set<Authority> findAllAuthoritiesByAuthorityGroupName(@Param("groupName") String groupName);

    static Set<AuthorityGroup> convertRawRecordToSet(Object[][] rawRecord) {
        Set<AuthorityGroup> resultSet = new HashSet<>();

        for (Object[] objects : rawRecord) {
            resultSet.add(new AuthorityGroup((BigInteger) objects[0], (String) objects[1]));
        }

        return resultSet;
    }
}
