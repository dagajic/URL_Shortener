package com.shortener.urlshortener.exception;

public class InvalidKeyException extends RuntimeException{

	private static final long serialVersionUID = -572183362052592731L;

	public InvalidKeyException(String message) {
		super(message);
	}
	
	

}
