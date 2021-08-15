package com.shortener.urlshortener.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shortener.urlshortener.security.encoder.PasswordEncoderTest;
import com.shortener.urlshortener.security.service.DatabaseUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private DatabaseUserDetailsService databaseUserDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers(HttpMethod.POST, new String[] { "/account"}).permitAll()
			.antMatchers(HttpMethod.GET, new String[] { "/{shortKey}", "/help"}).permitAll()
			.anyRequest().authenticated()
			.and().httpBasic();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/resources/**", 
				"/static/**", 
				"/css/**",
				"/images/**", 
				"/assets/**", 
				"/h2-console/**"
				);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		//TODO add BCrypt. This is for test only
	    return new PasswordEncoderTest();
	}
	
	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = 
			      new DaoAuthenticationProvider();
		provider.setUserDetailsService(databaseUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	  


	
}
