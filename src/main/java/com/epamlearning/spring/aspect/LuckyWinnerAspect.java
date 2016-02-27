package com.epamlearning.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epamlearning.spring.dao.impl.StatisticsRepositoryImpl;
import com.epamlearning.spring.domain.EventItem;

@Aspect
public class LuckyWinnerAspect {

	private static final double luckyRate = 0.8;
	private final static Logger logger = LoggerFactory.getLogger(LuckyWinnerAspect.class);

	@Autowired
	private StatisticsRepositoryImpl statRepository;

	@Around(value = "@annotation(com.epamlearning.spring.aspect.annotation.PriceRequest)")
	private Long savePriceRequest(final ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		EventItem event = (EventItem) args[1];
		statRepository.savePriceRequest(event.getId());
		if (Math.random() > luckyRate) {
			logger.debug("LuckyWinner detected for {} event", event.getEvent().getName());
			return 0L;
		}
		return (Long) joinPoint.proceed(args);
	}
}
