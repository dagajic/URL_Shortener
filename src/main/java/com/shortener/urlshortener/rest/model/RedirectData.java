package com.shortener.urlshortener.rest.model;

public class RedirectData {

	private String redirectUrl;
	private Integer redirectType;
		
	public RedirectData() {

	}
	
	public RedirectData(String redirectUrl, Integer redirectType) {
		this.redirectUrl = redirectUrl;
		this.redirectType = redirectType;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public Integer getRedirectType() {
		return redirectType;
	}
	public void setRedirectType(Integer redirectType) {
		this.redirectType = redirectType;
	}
	
	
}
