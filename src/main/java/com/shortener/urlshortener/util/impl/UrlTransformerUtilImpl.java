package com.shortener.urlshortener.util.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.shortener.urlshortener.util.UrlTransformerUtil;

@Component
public class UrlTransformerUtilImpl implements UrlTransformerUtil{

	@Override
	public String baseUrlFromCurrent(String url) throws MalformedURLException {
		URL currentUrl = new URL(url);
		String protocol = currentUrl.getProtocol();
		String host = currentUrl.getHost();
		int port = currentUrl.getPort();
		StringBuilder builder = new StringBuilder();
		builder.append(protocol);
		builder.append("://");
		builder.append(host);
		if(port != -1) {
			builder.append(":");
			builder.append(port);
		}
		return builder.toString();
	}

	@Override
	public String createUrl(String baseUrl, String key) {
		return baseUrl + "/" + key;
	}

}
