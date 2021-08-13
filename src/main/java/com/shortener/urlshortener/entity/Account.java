package com.shortener.urlshortener.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account {

	@Id
	private String id;
	
	private String password;
	
	@OneToMany(mappedBy="account")
	private List<RegisteredUrl> registeredUrls;
		
	public Account() {

	}

	public Account(String accountId, String password) {
		this.id = accountId;
		this.password = password;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<RegisteredUrl> getRegisteredUrls() {
		return registeredUrls;
	}

	public void setRegisteredUrls(List<RegisteredUrl> registeredUrls) {
		this.registeredUrls = registeredUrls;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
