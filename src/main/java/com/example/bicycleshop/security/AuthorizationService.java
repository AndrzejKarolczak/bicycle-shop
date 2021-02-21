package com.example.bicycleshop.security;

import com.example.bicycleshop.backend.entities.BusinessEntity;
import com.example.bicycleshop.exceptions.AccountAlreadyExists;
import com.example.bicycleshop.exceptions.NotFoundException;
import com.example.bicycleshop.security.entities.Account;
import com.example.bicycleshop.security.repositories.AccountRepository;
import com.example.bicycleshop.security.repositories.AuthorityGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class AuthorizationService implements UserDetailsService {
	private final AccountRepository accountRepository;
	private final AuthorityGroupRepository authorityGroupRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthorizationService(AccountRepository accountRepository, AuthorityGroupRepository authorityGroupRepository,
								PasswordEncoder passwordEncoder) {
		this.accountRepository = accountRepository;
		this.authorityGroupRepository = authorityGroupRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return accountRepository.findByLogin(login)
			.map(account -> new AccountDetails(account, accountRepository))
			.orElseThrow(() -> new UsernameNotFoundException("Account not found: " + login));
	}
	
	public BusinessEntity getBusinessEntityForLogin(String login) {
		return accountRepository.getAccountWithBusinessEntity(login)
			.map(account -> {
				BusinessEntity client = account.getBusinessEntity();
				if (Objects.isNull(client)) {
					throw new NotFoundException(BusinessEntity.class, login);
				}
				return client;
			})
			.orElseThrow(() -> new NotFoundException(Account.class, login));
	}
	
	public Long getBusinessEntityIdForLogin(String login) {
		return accountRepository.getAccountByLogin(login)
			.map(account -> {
				BusinessEntity client = account.getBusinessEntity();
				if (Objects.isNull(client)) {
					throw new NotFoundException(BusinessEntity.class, login);
				}
				return client.getBusinessEntityId();
			})
			.orElseThrow(() -> new NotFoundException(Account.class, login));
	}
	
	public Account getSavedAccount(String login, String rawPassword) {
		return accountRepository.findByLogin(login)
			.orElseGet(() -> authorityGroupRepository
				.findById(Roles.CLIENT.getAuthorityGroupNumber())
				.map(group -> {
					Account account = new Account(login, passwordEncoder.encode(rawPassword), group);
					account.setEnabled(true)
						.setCredentialsNonExpired(true)
						.setAccountNonExpired(true)
						.setAccountNonLocked(true);
					return accountRepository.save(account);
				}).orElseThrow(() -> new AccountAlreadyExists(login)));
	}
	
	
}
