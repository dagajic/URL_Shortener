package com.shortener.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shortener.urlshortener.entity.RegisteredUrl;

public interface RegisteredUrlRepository extends JpaRepository<RegisteredUrl, Long>{

}
