package com.shortener.urlshortener.enumeration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public enum RedirectTypeEnum {

	MOVED_PERMANENTLY(301, HttpStatus.MOVED_PERMANENTLY),
	FOUND(302, HttpStatus.FOUND);
	
	private Integer type;
	private HttpStatus httpStatus;
	
	private RedirectTypeEnum(Integer type, HttpStatus httpStatus) {
		this.type = type;
		this.httpStatus = httpStatus;
	}

	public Integer getType() {
		return type;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public static HttpStatus get(Integer type) {
		return lookup.get(type);
	}

	private static final Map<Integer, HttpStatus> lookup = new HashMap<>();

	static {
		for (RedirectTypeEnum pRedirectTypeEnum : RedirectTypeEnum.values()) {
			lookup.put(pRedirectTypeEnum.getType(), pRedirectTypeEnum.getHttpStatus());
		}
	}
	
	
}
