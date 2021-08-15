package com.shortener.urlshortener.controller;

import java.net.MalformedURLException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.urlshortener.request.AccountRequest;
import com.shortener.urlshortener.request.UrlRegistrationRequest;
import com.shortener.urlshortener.response.AccountResponse;
import com.shortener.urlshortener.response.UrlRegistrationResponse;
import com.shortener.urlshortener.service.AccountCreationService;
import com.shortener.urlshortener.service.StatisticService;
import com.shortener.urlshortener.service.UrlRegistrationService;
import com.shortener.urlshortener.util.UrlTransformerUtil;

@RestController
@RequestMapping("/api")
public class ConfigurationController {

	private static final Logger logger = LoggerFactory.getLogger(ConfigurationController.class);
	
	@Autowired
	private AccountCreationService accountCreationService;
	
	@Autowired
	private UrlRegistrationService urlRegistrationService;
	
	@Autowired
	private UrlTransformerUtil iUrlTransformerUtil;
	
	@Autowired
	private StatisticService iStatisticService;
	
	// account creation
	@PostMapping(value = "/account", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request){
		logger.info("AccountID: {}", request.getAccountId());		
		AccountResponse response = null;
		if(!StringUtils.hasText(request.getAccountId())) {
			response = new AccountResponse(false, "AccountId is mandatory");
			return new ResponseEntity<AccountResponse>(response, HttpStatus.BAD_REQUEST);
		}
		String password = accountCreationService.createAccount(request.getAccountId());
		response = new AccountResponse(true, "Your account is opened", password);
		return new ResponseEntity<AccountResponse>(response, HttpStatus.CREATED);
	}
	
	
	// URLs registration
	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UrlRegistrationResponse> registerUrl(@RequestBody UrlRegistrationRequest request, 
			HttpServletRequest servletRequest, Principal principal){
		logger.info("URL: {}, redirectType: {}",request.getUrl(), request.getRedirectType());
		// get account id from header		
		String shortKey = urlRegistrationService.registerUrl(request.getUrl(), 
				request.getRedirectType(), principal.getName());
		
		if(!StringUtils.hasText(shortKey)) {
			return new ResponseEntity<UrlRegistrationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// construct short url with short key
		String baseUrl = null;
		try {
			baseUrl = iUrlTransformerUtil.baseUrlFromCurrent(servletRequest.getRequestURL().toString());
		} catch (MalformedURLException e) {
			return new ResponseEntity<UrlRegistrationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String shortUrl = iUrlTransformerUtil.createUrl(baseUrl, shortKey);
		UrlRegistrationResponse response = new UrlRegistrationResponse(shortUrl); 
		return new ResponseEntity<UrlRegistrationResponse>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/statistic/{accountId}")
	public ResponseEntity<Map<String, Long>> statistics(@PathVariable String accountId, Principal principal){
		if(StringUtils.hasText(principal.getName()) && !principal.getName().equals(accountId)) {
			return new ResponseEntity<Map<String,Long>>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Fetching statistics for account: {}", accountId);
		Map<String, Long> result = iStatisticService.getStatistic(accountId);
		
		return new ResponseEntity<Map<String,Long>>(result, HttpStatus.OK);
	}
	
}
