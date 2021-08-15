package com.shortener.urlshortener.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.urlshortener.entity.RegisteredUrl;
import com.shortener.urlshortener.entity.UrlCall;
import com.shortener.urlshortener.exception.InvalidKeyException;
import com.shortener.urlshortener.repository.RegisteredUrlRepository;
import com.shortener.urlshortener.repository.UrlCallRepository;
import com.shortener.urlshortener.rest.model.RedirectData;
import com.shortener.urlshortener.service.RedirectService;
import com.shortener.urlshortener.util.UrlShortenerUtil;

@Service
public class RedirectServiceImpl implements RedirectService{
	
	private static final Logger logger = LoggerFactory.getLogger(RedirectServiceImpl.class);
	
	@Autowired
	private UrlShortenerUtil urlShortenerUtil;
	
	@Autowired
	private RegisteredUrlRepository iRegisteredUrlRepository;
	
	@Autowired
	private UrlCallRepository urlCallRepository;

	@Override
	public RedirectData getOriginalUrl(String shortKey) {
		Long id = urlShortenerUtil.decode(shortKey);
		Optional<RegisteredUrl> registeredUrl = iRegisteredUrlRepository.findById(id);
		if(registeredUrl.isEmpty()) {
			throw new InvalidKeyException("Invalid short key");
		}
		String url = registeredUrl.get().getOriginalUrl();
		Integer redirectType = registeredUrl.get().getRedirectType();
		//save UrlCall
		UrlCall urlCall = new UrlCall(registeredUrl.get());
		urlCallRepository.insert(urlCall);
		RedirectData data = new RedirectData(url, redirectType);
		return data;
	}

}
