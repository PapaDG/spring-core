package com.epamlearning.spring.aspects;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;

import com.epamlearning.spring.BaseTest;
import com.epamlearning.spring.dao.impl.StatisticsRepositoryImpl;
import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Rating;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.AuditoriumService;
import com.epamlearning.spring.sevice.BookingService;
import com.epamlearning.spring.sevice.EventService;
import com.epamlearning.spring.sevice.UserService;

public class CounterAspectTest extends BaseTest {

	@Autowired
	private StatisticsRepositoryImpl statRepository;
	@Autowired
	private EventService eventService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuditoriumService auditoriumService;

	@Test
	public void testSaveEventRequest() {
		Event event = eventService.create(getAuditoriumName(), 100L, Rating.AVERAGE);
		for (int i = 0; i < getCounter(); i++) {
			eventService.getByName(getAuditoriumName());
		}
		assertEquals(statRepository.getStatisticEventById(event.getId()), getCounter());
	}

	@Test
	public void testSaveBookingRequest() {
		User user = userService.register(getUserName(), getUserEmail());
		Event event = eventService.create(getEventname(), getPrice(), Rating.AVERAGE);
		Auditorium auditorium = auditoriumService.getAuditoriumByName(getAuditoriumName());
		EventItem show = eventService.assignAuditorium(event, auditorium, getTime());
		int seat = 10;
		for (int i = 0; i < getCounter(); i++) {
			bookingService.bookTicket(user, show, seat + i);
		}
		assertEquals(statRepository.getStatisticBookingById(event.getId()), getCounter());
	}

}
