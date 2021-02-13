package com.example.bicycleshop.security.entities;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Subselect("SELECT u.account_id, ag.group_id, a.authority_id, u.login, u.password, u.is_account_non_expired, " +
    "u.is_account_non_locked, u.is_credentials_non_expired, u.is_enabled, ag.group_name, a.authority_name FROM accounts u " +
    "JOIN authority_groups ag ON ag.group_id = u.group_id " +
    "JOIN authority_in_group aig ON aig.group_id = ag.group_id " +
    "JOIN authorities a ON a.authority_id = aig.authority_id ")
public class FullAccountSecurityData {
    @EmbeddedId
    private FullAccountSecurityDataId id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private boolean isAccountNonExpired = false;

    @Column
    private boolean isAccountNonLocked = false;

    @Column
    private boolean isCredentialsNonExpired = false;

    @Column
    private boolean isEnabled = false;

    @Column
    private String groupName;

    @Column
    private String authorityName;
}
