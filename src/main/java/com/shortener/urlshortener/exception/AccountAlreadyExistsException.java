package com.shortener.urlshortener.exception;

public class AccountAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 8657184213366563010L;

	public AccountAlreadyExistsException(String message) {
		super(message);
	}
	
	

}
