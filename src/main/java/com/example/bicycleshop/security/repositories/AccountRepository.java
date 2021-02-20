package com.example.bicycleshop.security.repositories;

import com.example.bicycleshop.security.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByLogin(String login);
    
    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.businessEntity WHERE a.login = :login")
    Optional<Account> getAccountWithBusinessEntity(@Param("login") String login);

    @Query(nativeQuery = true,
        value = "SELECT a.authority_id AS id, a.authority_name AS name FROM accounts u " +
            "JOIN authority_groups ag ON ag.group_id = u.group_id " +
            "JOIN authority_in_group aig ON aig.group_id = ag.group_id " +
            "JOIN authorities a ON a.authority_id = aig.authority_id " +
            "WHERE u.login = :login")
    Object[][] findAccountAuthoritiesByLogin(@Param("login") String name);

    @Query(nativeQuery = true,
        value = "SELECT ag.group_id AS id, ag.group_name AS name FROM accounts u " +
            "JOIN authority_groups ag ON ag.group_id = u.group_id " +
            "WHERE u.login = :login")
    Object[][] findAccountAuthorityGroupByLogin(@Param("login") String name);
}
