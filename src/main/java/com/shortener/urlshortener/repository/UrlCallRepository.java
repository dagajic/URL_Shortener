package com.shortener.urlshortener.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.shortener.urlshortener.dto.StatisticDTO;
import com.shortener.urlshortener.entity.Account;
import com.shortener.urlshortener.entity.RegisteredUrl;
import com.shortener.urlshortener.entity.UrlCall;

@Repository
@Transactional
public class UrlCallRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Long insert(UrlCall urlCall) {
		
		entityManager.persist(urlCall);
		return urlCall.getId();
	}
	
	public List<StatisticDTO> getUrlCallsStatistic(String accountId){
		List<StatisticDTO> result = null;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<StatisticDTO> cq = cb.createQuery(StatisticDTO.class);
		//FROM
		Root<UrlCall> urlCall = cq.from(UrlCall.class);
		//JOIN
		Join<UrlCall, RegisteredUrl> registeredUrl = urlCall.join("registeredUrl");
		Join<RegisteredUrl, Account> account = registeredUrl.join("account");
		//SELECT
		Expression<String> groupByExp = registeredUrl.get("originalUrl").as(String.class);
		Expression<Long> countExp = cb.count(groupByExp);
		cq.select(cb.construct(StatisticDTO.class, 
				registeredUrl.get("originalUrl"), countExp));
		//WHERE
		ParameterExpression<String> paramAccountId = cb.parameter(String.class);
		cq.where(cb.equal(account.get("id"), paramAccountId));
		cq.groupBy(groupByExp);
		//execute
		TypedQuery<StatisticDTO> query = entityManager.createQuery(cq);
		query.setParameter(paramAccountId, accountId);
		result = query.getResultList();
		return result;
	}
}
