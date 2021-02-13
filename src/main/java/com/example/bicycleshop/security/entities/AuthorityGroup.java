package com.example.bicycleshop.security.entities;

import com.example.bicycleshop.validation.ValidationMessages;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authority_groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"name"})
@EqualsAndHashCode(exclude = {"accounts", "authorities"})
public class AuthorityGroup {
    @Id
    @Column(name = "group_id", updatable = false, nullable = false)
    private long authorityGroupId;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(name = "group_name", nullable = false, length = 50, unique = true)
    private String name;

    @OneToMany(mappedBy = "authorityGroup",
        fetch = FetchType.LAZY,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(mappedBy = "authorityGroups",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    private Set<Authority> authorities = new HashSet<>();

    public AuthorityGroup(BigInteger authorityGroupId, String groupName) {
        this.authorityGroupId = authorityGroupId.longValue();
        this.name = groupName;
    }

    public void addUser(Account account){
        accounts.add(account);
    }

    public void addAuthority(Authority authority){
        authorities.add(authority);
    }
}
