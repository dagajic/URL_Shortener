package com.shortener.urlshortener.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlRegistrationRequest {

	@JsonProperty(value = "URL", required = true)
	private String url;
	private Integer redirectType;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getRedirectType() {
		return redirectType;
	}
	public void setRedirectType(Integer redirectType) {
		this.redirectType = redirectType;
	}
	
	
	
}
