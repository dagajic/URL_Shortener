package com.shortener.urlshortener.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shortener.urlshortener.exception.AccountAlreadyExistsException;
import com.shortener.urlshortener.exception.ValidationException;
import com.shortener.urlshortener.response.AccountResponse;
import com.shortener.urlshortener.response.ErrorResponse;

@ControllerAdvice
public class ExceptionHandlerConfiguration {

	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<AccountResponse> handleAccountCreationException(AccountAlreadyExistsException ex){
		AccountResponse response = new AccountResponse(false, ex.getMessage());
		return new ResponseEntity<AccountResponse>(response, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> handleAccountCreationException(ValidationException ex){
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
