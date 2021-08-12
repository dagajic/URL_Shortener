package com.shortener.urlshortener.util;

public interface UrlShortenerUtil {

	String encode(Long number);
	Long decode(String key);
}
