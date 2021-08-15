package com.shortener.urlshortener.validator.impl;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.shortener.urlshortener.exception.ValidationException;
import com.shortener.urlshortener.validator.UrlRegistrationValidator;

@Component
public class UrlRegistrationValidatorImpl implements UrlRegistrationValidator{
	
	private static final Logger logger = LoggerFactory.getLogger(UrlRegistrationValidatorImpl.class);

	@Override
	public void validate(String url, Integer redirectType) {
		logger.info("Validating URL: {}, redirectType: {}", url, redirectType);
		if(!StringUtils.hasText(url)) {
			throw new ValidationException("URL is mandatory");
		}
		if(redirectType != null && (
				HttpStatus.FOUND.value() != redirectType.intValue() 
				&& HttpStatus.MOVED_PERMANENTLY.value() != redirectType.intValue())) {
			throw new ValidationException("Invalid optional property redirectType. Accepted types: 301 | 302 (default)");
		}
		UrlValidator urlValidator = new UrlValidator(new String[] {"http","https"});
		if(!urlValidator.isValid(url)) {
			throw new ValidationException("Url is invalid");
		}
	
	}

}
