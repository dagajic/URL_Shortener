package com.shortener.urlshortener.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UrlRegistrationResponse {

	@JsonInclude(Include.NON_NULL)
	private String shortUrl;		

	public UrlRegistrationResponse() {
		
	}
	
	public UrlRegistrationResponse(String shortUrl) {
		this.shortUrl = shortUrl;
	}


	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	
}
