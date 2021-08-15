package com.shortener.urlshortener.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.urlshortener.dto.StatisticDTO;
import com.shortener.urlshortener.repository.UrlCallRepository;
import com.shortener.urlshortener.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService{
	
	private static final Logger logger = LoggerFactory.getLogger(StatisticServiceImpl.class);
	
	@Autowired
	private UrlCallRepository urlCallRepository;

	@Override
	public Map<String, Long> getStatistic(String accountId) {
		List<StatisticDTO> statistic = urlCallRepository.getUrlCallsStatistic(accountId);
//		logger.info("Statistics lenght: {}", statistic.size());
		Map<String, Long> result = toMap(statistic);
		return result;
	}
	
	private Map<String, Long> toMap(List<StatisticDTO> statistic){
		Map<String, Long> map = new HashMap<>();
		if(statistic == null) {
			return map;
		}
		for (StatisticDTO statisticDTO : statistic) {
			map.put(statisticDTO.getOriginalUrl(), statisticDTO.getCount());
		}
		return map;
	}

}
