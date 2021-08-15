package com.shortener.urlshortener.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.urlshortener.enumeration.RedirectTypeEnum;
import com.shortener.urlshortener.rest.model.RedirectData;
import com.shortener.urlshortener.service.RedirectService;

@RestController
public class RedirectController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
	
	@Autowired
	private RedirectService iRedirectService;
	
	// redirect
	@GetMapping("/{shortKey}")
	public ResponseEntity<Void> redirect(@PathVariable String shortKey) {
		logger.info("Redirect with key: {}", shortKey);
		RedirectData redirectData = iRedirectService.getOriginalUrl(shortKey);
		HttpStatus httpStatus = RedirectTypeEnum.get(redirectData.getRedirectType());
		return ResponseEntity.status(httpStatus).location(URI.create(redirectData.getRedirectUrl())).build();
	}
	
	
	
	
}
