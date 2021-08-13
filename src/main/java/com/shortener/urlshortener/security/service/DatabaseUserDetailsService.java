package com.shortener.urlshortener.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shortener.urlshortener.entity.Account;
import com.shortener.urlshortener.repository.AccountRepository;
import com.shortener.urlshortener.security.model.UserDetailsImpl;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepository iAccountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = iAccountRepository.findById(username);
		if(account.isEmpty()) {
			throw new UsernameNotFoundException("Invalid account");
		}
		UserDetails details = new UserDetailsImpl(account.get().getId(), account.get().getPassword());
		return details;
	}

}
