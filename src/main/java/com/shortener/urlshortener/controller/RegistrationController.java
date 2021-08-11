package com.shortener.urlshortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.urlshortener.request.AccountRequest;
import com.shortener.urlshortener.response.AccountResponse;
import com.shortener.urlshortener.service.AccountCreationService;

@RestController
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private AccountCreationService accountCreationService;
	
	// account creation
	@PostMapping(value = "/account", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request){
		logger.info("AccountID: " + request.getAccountId());		
		AccountResponse response = null;
		if(!StringUtils.hasText(request.getAccountId())) {
			response = new AccountResponse(false, "AccountId is mandatory");
			return new ResponseEntity<AccountResponse>(response, HttpStatus.BAD_REQUEST);
		}
		String password = accountCreationService.createAccount(request.getAccountId());
		response = new AccountResponse(true, "Your account is opened", password);
		return new ResponseEntity<AccountResponse>(response, HttpStatus.OK);
	}
	
	
	// URLs registration
}
