package com.shortener.urlshortener.service;

import java.util.Map;

public interface StatisticService {

	Map<String, Long> getStatistic(String accountId);
}
