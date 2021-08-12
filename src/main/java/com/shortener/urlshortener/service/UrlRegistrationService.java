package com.shortener.urlshortener.service;

public interface UrlRegistrationService {

	String registerUrl(String url, Integer redirectType, String accountId);
}
