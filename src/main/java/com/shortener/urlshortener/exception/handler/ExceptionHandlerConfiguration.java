package com.shortener.urlshortener.exception.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shortener.urlshortener.exception.AccountAlreadyExistsException;
import com.shortener.urlshortener.exception.ExceptionResponse;
import com.shortener.urlshortener.exception.InvalidKeyException;
import com.shortener.urlshortener.exception.ValidationException;
import com.shortener.urlshortener.response.AccountResponse;
import com.shortener.urlshortener.response.ErrorResponse;

@ControllerAdvice
public class ExceptionHandlerConfiguration extends ResponseEntityExceptionHandler{

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

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, status);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Method argument not valid",
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, status);
	}
	
	@ExceptionHandler(InvalidKeyException.class)
	public final ResponseEntity<Object> handleInvalidKeyException(InvalidKeyException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//				request.getDescription(false));
//		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
