package com.shortener.urlshortener.service.impl;


import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.urlshortener.entity.Account;
import com.shortener.urlshortener.exception.AccountCreationException;
import com.shortener.urlshortener.repository.AccountRepository;
import com.shortener.urlshortener.service.AccountCreationService;


@Service
public class AccountCreationServiceImpl implements AccountCreationService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public String createAccount(String accountId) {
		// TODO check if account already exists
		Optional<Account> account = accountRepository.findById(accountId);
		if(account.isPresent()) {
			throw new AccountCreationException("Account already exists");
		}
		String password = RandomStringUtils.randomAlphanumeric(8);
		accountRepository.save(new Account(accountId, password));
		return password;
	}

}
