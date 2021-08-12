package com.shortener.urlshortener.util;

import java.net.MalformedURLException;

public interface UrlTransformerUtil {

	String baseUrlFromCurrent(String url) throws MalformedURLException;
	String createUrl(String baseUrl, String key);
}
