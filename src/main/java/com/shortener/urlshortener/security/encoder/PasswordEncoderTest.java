package com.shortener.urlshortener.security.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		boolean isEqual = rawPassword.toString().equals(encodedPassword);
		return isEqual;
	}

}
