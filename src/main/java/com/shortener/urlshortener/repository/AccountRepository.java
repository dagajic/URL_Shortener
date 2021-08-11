package com.shortener.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shortener.urlshortener.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
