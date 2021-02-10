package com.example.bicycleshop.security;

import com.example.bicycleshop.security.dao.AuthorityGroupRepository;
import com.example.bicycleshop.security.dao.AuthorityRepository;
import com.example.bicycleshop.security.dao.AccountRepository;
import com.example.bicycleshop.security.model.Account;
import com.example.bicycleshop.security.model.Authority;
import com.example.bicycleshop.security.model.AuthorityGroup;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
public class AccountDetails implements UserDetails {
    private final Account account;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Object[][] rawAuthorityGroup = accountRepository.findAccountAuthorityGroupByLogin(account.getLogin());
        Set<AuthorityGroup> authorityGroupSet = AuthorityGroupRepository.convertRawRecordToSet(rawAuthorityGroup);
        AuthorityGroup authorityGroup = extractFirstAuthorityGroup(authorityGroupSet);

        account.setAuthorityGroup(authorityGroup);
        authorityGroup.addUser(account);

        Object[][] rawAuthorities = accountRepository.findAccountAuthoritiesByLogin(account.getLogin());
        Set<Authority> authorities = AuthorityRepository.convertRawRecordToSet(rawAuthorities);

        authorities.forEach(a -> {
            authorityGroup.addAuthority(a);
            a.addAuthorityGroup(authorityGroup);
        });

        return account.getAuthorityGroup().getAuthorities() ;
    }

    private AuthorityGroup extractFirstAuthorityGroup(Set<AuthorityGroup> authorityGroupSet) {
        return authorityGroupSet.toArray(new AuthorityGroup[0])[0];
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return account.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return account.isEnabled();
    }
}
