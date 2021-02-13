package com.example.bicycleshop.security;

import com.example.bicycleshop.security.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Primary
@Service
@Transactional
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return accountRepository.findByLogin(login)
            .map(account -> new AccountDetails(account, accountRepository))
            .orElseThrow(() -> new UsernameNotFoundException("Account not found: " + login));
    }
}
