package com.shortener.urlshortener.validator;

public interface UrlRegistrationValidator {

	void validate(String url, Integer redirectType);
}
