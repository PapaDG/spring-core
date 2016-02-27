package com.epamlearning.spring.aspects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epamlearning.spring.BaseTest;
import com.epamlearning.spring.dao.impl.StatisticsRepositoryImpl;
import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Rating;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.AuditoriumService;
import com.epamlearning.spring.sevice.DiscountService;
import com.epamlearning.spring.sevice.EventService;
import com.epamlearning.spring.sevice.UserService;

public class DiscountAspectTest extends BaseTest {

	@Autowired
	private StatisticsRepositoryImpl statRepository;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	private AuditoriumService auditoriumService;

	@Test
	public void testDiscountRequest() {
		final User user = userService.register(getUserName(), getUserEmail());
		user.setBirthday(getTime());
		final Event event = eventService.create(getEventname(), getPrice(), Rating.AVERAGE);
		final Auditorium auditorium = auditoriumService.getAuditoriumByName(getAuditoriumName());
		final EventItem show = eventService.assignAuditorium(event, auditorium, getTime());
		for (int i = 0; i < getCounter(); i++) {
			discountService.getDiscount(user, show);
		}

		assertEquals(statRepository.getStatisticBonusDiscountById(user.getId()), getCounter());
		assertEquals(statRepository.getStatisticBirthdayDiscountById(user.getId()), getCounter());
	}

}
