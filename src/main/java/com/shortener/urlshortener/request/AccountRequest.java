package com.shortener.urlshortener.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {

	@JsonProperty(value = "AccountId", required = true)
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
}
