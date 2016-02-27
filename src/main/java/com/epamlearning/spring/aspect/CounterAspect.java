package com.epamlearning.spring.aspect;

import com.epamlearning.spring.dao.impl.StatisticsRepositoryImpl;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.EventItem;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class CounterAspect {
	private final static Logger logger = LoggerFactory.getLogger(CounterAspect.class);

	@Autowired
	private StatisticsRepositoryImpl statRepository;

	@AfterReturning(pointcut = "@annotation(com.epamlearning.spring.aspect.annotation.EventRequest)", returning = "event")
	private void saveEventRequest(final JoinPoint joinPoint, final Event event) {
		logger.debug("Event requested by {}", event.getName());
		statRepository.saveEventRequest(event.getId());
	}

	@Before(value = "@annotation(com.epamlearning.spring.aspect.annotation.BookingRequest)")
	private void saveBookingRequest(final JoinPoint joinPoint) {
		EventItem event = (EventItem) joinPoint.getArgs()[1];
		logger.debug("Booking request for eventItem  {}", event.getId());
		statRepository.saveBookingRequest(event.getEvent().getId());
	}

}
