package com.shortener.urlshortener.dto;

public class StatisticDTO {

	private String originalUrl;
	private Long count;
			
	public StatisticDTO() {

	}
		
	
	public StatisticDTO(String originalUrl) {
		this.originalUrl = originalUrl;
	}


	public StatisticDTO(String originalUrl, Long count) {

		this.originalUrl = originalUrl;
		this.count = count;
	}
	
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
