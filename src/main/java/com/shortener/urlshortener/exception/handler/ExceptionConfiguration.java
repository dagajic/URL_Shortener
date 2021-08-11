package com.shortener.urlshortener.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shortener.urlshortener.exception.AccountCreationException;
import com.shortener.urlshortener.response.AccountResponse;

@ControllerAdvice
public class ExceptionConfiguration {

	@ExceptionHandler(AccountCreationException.class)
	public ResponseEntity<AccountResponse> handleAccountCreationException(AccountCreationException ex){
		AccountResponse response = new AccountResponse(false, ex.getMessage());
		return new ResponseEntity<AccountResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
