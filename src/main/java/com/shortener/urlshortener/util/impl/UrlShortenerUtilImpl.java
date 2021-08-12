package com.shortener.urlshortener.util.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.shortener.urlshortener.util.UrlShortenerUtil;

@Component
public class UrlShortenerUtilImpl implements UrlShortenerUtil{
	
	private static final Logger logger = LoggerFactory.getLogger(UrlShortenerUtilImpl.class);
	
	public static final String ALPHANUMERIC = "PQW9jkSB6qmLCNR4vsGbDVx7pHnKXhgiZY0lTJwM1a2utOEA38rI5oydFcefzU";
	public static final int BASE = ALPHANUMERIC.length();

	@Override
	public String encode(Long number) {
//		logger.info("BASE : {}", BASE);
		StringBuilder builder = new StringBuilder();
		int remainder = 0;
		while(number > 0) {
			remainder = (int) (number % BASE);
			builder.insert(0, ALPHANUMERIC.charAt(remainder));
			number = number / BASE;
//			logger.info("remain: {}, number: {}", remainder, number);
		}
		String result = builder.toString();
		return result;
	}

	@Override
	public Long decode(String key) {
		Long number = 0L;
        for (int i = 0; i < key.length(); i++) {
        	number = number * BASE + ALPHANUMERIC.indexOf(key.charAt(i));
        }
        return number;
	}

}
