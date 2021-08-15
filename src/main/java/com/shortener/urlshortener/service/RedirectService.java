package com.shortener.urlshortener.service;

import com.shortener.urlshortener.rest.model.RedirectData;

public interface RedirectService {

	RedirectData getOriginalUrl(String shortKey);
}
