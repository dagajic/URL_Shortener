package com.shortener.urlshortener.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class RegisteredUrl {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_reg_url_seq")
	@SequenceGenerator(name = "gen_reg_url_seq", sequenceName = "reg_url_seq", initialValue = 12345, allocationSize = 1)
	private Long id;
	
	@Column(length = 2048)
	private String originalUrl;
	
	private Integer redirectType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Account account;
	
	@OneToMany(mappedBy="registeredUrl", fetch = FetchType.LAZY)
	private List<UrlCall> urlCalls;
		
	public RegisteredUrl() {

	}
	
	public RegisteredUrl(String originalUrl, Integer redirectType) {
		this.originalUrl = originalUrl;
		this.redirectType = redirectType;
	}

	public RegisteredUrl(String originalUrl, Integer redirectType, Account account) {
		this.originalUrl = originalUrl;
		this.redirectType = redirectType;
		this.account = account;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public Integer getRedirectType() {
		return redirectType;
	}
	public void setRedirectType(Integer redirectType) {
		this.redirectType = redirectType;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	public List<UrlCall> getUrlCalls() {
		return urlCalls;
	}
	
	
}
