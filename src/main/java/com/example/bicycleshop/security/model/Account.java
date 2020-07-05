package com.example.bicycleshop.security.model;

import com.example.bicycleshop.backend.model.BusinessEntity;
import com.example.bicycleshop.helpers.ValidationMessages;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"accountId", "login"})
@Accessors(chain = true)
public class Account {
    private static final String COLUMN_DEFINITION = "bit default 0"; //"boolean default false"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long accountId;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @NotEmpty(message = ValidationMessages.NOT_EMPTY)
    @Column(nullable = false, length = 255)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "group_id")
    private AuthorityGroup authorityGroup;

    public Account(String login, String password, AuthorityGroup authorityGroup) {
        this.login = login;
        this.password = password;
        this.authorityGroup = authorityGroup;
    }

    @Column(nullable = false, columnDefinition = COLUMN_DEFINITION)
    private boolean isAccountNonExpired = false;

    @Column(nullable = false, columnDefinition = COLUMN_DEFINITION)
    private boolean isAccountNonLocked = false;

    @Column(nullable = false, columnDefinition = COLUMN_DEFINITION)
    private boolean isCredentialsNonExpired = false;

    @Column(nullable = false, columnDefinition = COLUMN_DEFINITION)
    private boolean isEnabled = false;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private BusinessEntity businessEntity;

    @Override
    public String toString() {
        return "User{" +
            "userName='" + getLogin() + '\'' +
            ", password='" + getPassword() + '\'' +
            ", isAccountNonExpired=" + isAccountNonExpired +
            ", isAccountNonLocked=" + isAccountNonLocked +
            ", isCredentialsNonExpired=" + isCredentialsNonExpired +
            ", isEnabled=" + isEnabled +
            ", authorityGroupName=" + getAuthorityGroup().getName() +
            '}';
    }
}
