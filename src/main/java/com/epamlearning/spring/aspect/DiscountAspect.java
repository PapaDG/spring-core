package com.epamlearning.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epamlearning.spring.dao.StatisticRepository;
import com.epamlearning.spring.domain.User;

@Aspect
public class DiscountAspect {
	private final static Logger logger = LoggerFactory.getLogger(DiscountAspect.class);

	@Autowired
	private StatisticRepository statRepository;

	@AfterReturning(pointcut = "execution(* com.epamlearning.spring.strategy.DiscountStrategy.getDiscount(..))", returning = "result")
	private void saveDiscountRequest(final JoinPoint joinPoint, final int result) {
		final User user = (User) joinPoint.getArgs()[0];
		final String discountType = joinPoint.getTarget().getClass().getSimpleName();
		logger.debug("{} is receiving {}% discount via {}", new Object[] { user.getName(), result, discountType });
		statRepository.saveDiscountRequest(discountType, user);
	}
}
