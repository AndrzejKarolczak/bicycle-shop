package com.example.bicycleshop.security.entities;

import com.example.bicycleshop.validation.ValidationMessages;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"name"})
@EqualsAndHashCode(exclude = "authorityGroups")
public class Authority implements GrantedAuthority {
    @Id
    @Column(name = "authority_id", updatable = false, nullable = false)
    private long authorityId;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(name = "authority_name", nullable = false, length = 50, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "authority_in_group",
        joinColumns = @JoinColumn(name = "authority_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<AuthorityGroup> authorityGroups = new HashSet<>();

    public Authority(BigInteger authorityId, String authorityName) {
        this.authorityId = authorityId.longValue();
        this.name = authorityName;
    }

    public String getAuthority() {
        return name;
    }

    public void addAuthorityGroup(AuthorityGroup authorityGroup){
        authorityGroups.add(authorityGroup);
    }
}
