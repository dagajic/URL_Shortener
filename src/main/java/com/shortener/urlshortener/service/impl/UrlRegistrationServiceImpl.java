package com.shortener.urlshortener.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shortener.urlshortener.entity.Account;
import com.shortener.urlshortener.entity.RegisteredUrl;
import com.shortener.urlshortener.repository.AccountRepository;
import com.shortener.urlshortener.repository.RegisteredUrlRepository;
import com.shortener.urlshortener.service.UrlRegistrationService;
import com.shortener.urlshortener.util.UrlShortenerUtil;
import com.shortener.urlshortener.validator.UrlRegistrationValidator;

@Service
public class UrlRegistrationServiceImpl implements UrlRegistrationService{
	
	private static final Logger logger = LoggerFactory.getLogger(UrlRegistrationServiceImpl.class);
	
	@Value( "${default.redirect.type}" )
	private Integer defaultRedirectType;
	
	@Autowired
	private UrlRegistrationValidator urlRegistrationValidator;
	
	@Autowired
	private UrlShortenerUtil urlShortenerUtil;
	
	@Autowired
	private RegisteredUrlRepository iRegisteredUrlRepository;
	
	@Autowired
	private AccountRepository iAccountRepository;

	@Override
	public String registerUrl(String url, Integer redirectType, String accountId) {
		if(redirectType == null) {
			redirectType = defaultRedirectType;
		}
		logger.info("Start validating url registration request");
		urlRegistrationValidator.validate(url, redirectType);
		// save url		
		Optional<Account> account = iAccountRepository.findById(accountId);
		if(account.isPresent()) {
			RegisteredUrl newUrl = new RegisteredUrl(url, redirectType, account.get());
			RegisteredUrl savedUrl = iRegisteredUrlRepository.save(newUrl);
			// create short key by ID
			String shortKey = urlShortenerUtil.encode(savedUrl.getId());
//			Long number = urlShortenerUtil.decode(shortKey);
//			logger.info("encoded: {}, decoded: {}", shortKey, number);
			return shortKey;
		}
		
		return null;
	}

	
}
