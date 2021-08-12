package com.shortener.urlshortener.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

	@Override
	public String registerUrl(String url, Integer redirectType, String accountId) {
		if(redirectType == null) {
			redirectType = defaultRedirectType;
		}
		logger.info("Start validating url registration request");
		urlRegistrationValidator.validate(url, redirectType);
		// save url
		
		
		// create short key by ID
		String shortKey = urlShortenerUtil.encode(2589785l);
//		Long number = urlShortenerUtil.decode(shortKey);
//		logger.info("encoded: {}, decoded: {}", shortKey, number);
				
		return shortKey;
	}

	
}
