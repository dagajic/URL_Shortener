package com.shortener.urlshortener.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class UrlCall {

	@Id
	@GeneratedValue
	private Long id;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private RegisteredUrl registeredUrl;
		
	public UrlCall() {

	}
	
	public UrlCall(RegisteredUrl registeredUrl) {
		this.registeredUrl = registeredUrl;
	}


	public RegisteredUrl getRegisteredUrl() {
		return registeredUrl;
	}

	public void setRegisteredUrl(RegisteredUrl registeredUrl) {
		this.registeredUrl = registeredUrl;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Long getId() {
		return id;
	}
	
	
}
