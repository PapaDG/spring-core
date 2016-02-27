package com.epamlearning.spring.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epamlearning.spring.BaseTest;
import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Rating;
import com.epamlearning.spring.domain.Ticket;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.AuditoriumService;
import com.epamlearning.spring.sevice.DiscountService;
import com.epamlearning.spring.sevice.EventService;
import com.epamlearning.spring.sevice.UserService;

public class DiscountServiceTest extends BaseTest {

	@Autowired
	private DiscountService discountService;
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	private AuditoriumService auditoriumService;

	@Test
	public void testBirthdayDiscount() {
		final User user = userService.register(getUserName(), getUserEmail());
		user.setBirthday(getTime());
		final Event event = eventService.create(getEventname(), getPrice(), Rating.AVERAGE);
		final Auditorium auditorium = auditoriumService.getAuditoriumByName(getAuditoriumName());
		final EventItem show = eventService.assignAuditorium(event, auditorium, getTime());

		assertEquals(discountService.getDiscount(user, show), 5);
		assertEquals(discountService.getDiscount(new User(), show), 0);
	}

	@Test
	public void testBonusDiscount() {
		final User user = userService.register(getUserName(), getUserEmail());
		user.setBirthday(getTime());
		for (int i = 0; i < 20; i++) {
			user.getTickets().add(new Ticket());
		}
		final Event event = eventService.create(getEventname(), getPrice(), Rating.AVERAGE);
		final Auditorium auditorium = auditoriumService.getAuditoriumByName(getAuditoriumName());
		final EventItem show = eventService.assignAuditorium(event, auditorium, getTime());

		assertEquals(discountService.getDiscount(user, show), 50);
		assertEquals(discountService.getDiscount(new User(), show), 0);
	}
}
