package com.example.bicycleshop.security;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.exceptions.NotFoundException;
import com.example.bicycleshop.security.entities.Account;
import com.example.bicycleshop.security.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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
	
	public BusinessEntity getBusinessEntityForLogin(String login) {
		return accountRepository.getAccountWithBusinessEntity(login).map(account -> {
			BusinessEntity client = account.getBusinessEntity();
			if (Objects.isNull(client)) {
				throw new NotFoundException(BusinessEntity.class, login);
			}
			return client;
		}).orElseThrow(() -> new NotFoundException(Account.class, login));
	}
}
