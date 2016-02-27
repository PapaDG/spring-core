package com.epamlearning.spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epamlearning.spring.BaseTest;
import com.epamlearning.spring.dao.TicketRepository;
import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Rating;
import com.epamlearning.spring.domain.Ticket;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.AuditoriumService;
import com.epamlearning.spring.sevice.BookingService;
import com.epamlearning.spring.sevice.EventService;
import com.epamlearning.spring.sevice.UserService;

public class BookingServiceTest extends BaseTest {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private AuditoriumService auditoriumService;
	@Autowired
	private EventService eventService;
	@Autowired
	private UserService userService;
	@Autowired
	private TicketRepository ticketRepository;

	@Test
	public void testGetTicketPrice() {
		final User user = userService.register(getUserName(), getUserEmail());
		user.setBirthday(getTime());
		final Event event = eventService.create(getEventname(), getPrice(), Rating.AVERAGE);
		final Auditorium auditorium = auditoriumService.getAuditoriumByName(getAuditoriumName());
		final EventItem show = eventService.assignAuditorium(event, auditorium, getTime());
		final List<Integer> seats = new ArrayList<Integer>();
		// vip(11)+standart(5)
		seats.add(11);
		seats.add(5);
		final Long userPrice = bookingService.getTicketPrice(user, show, seats, getTime());
		// userPrice can be 0 since user can win free ticket
		assertNotNull(userPrice);
	}

	@Test
	public void testBookTicket() {
		final User user = userService.register(getUserName(), getUserEmail());
		final Event event = eventService.create(getEventname(), getPrice(), Rating.AVERAGE);
		final Auditorium auditorium = auditoriumService.getAuditoriumByName(getAuditoriumName());
		final EventItem show = eventService.assignAuditorium(event, auditorium, getTime());
		final Ticket ticket = bookingService.bookTicket(user, show, getSeat());

		assertEquals(ticket.getUser().getName(), getUserName());
		assertEquals((int) ticket.getSeat(), getSeat());
		assertEquals(ticket.getItem().getEvent().getName(), getEventname());

		assertEquals(ticketRepository.getTicketsForShow(show).get(0).getId(), ticket.getId());
	}

	@Test
	public void testGetTicketsForShow() {
		final User user = userService.register(getUserName(), getUserEmail());
		final Event event = eventService.create(getEventname(), getPrice(), Rating.AVERAGE);
		final Auditorium auditorium = auditoriumService.getAuditoriumByName(getAuditoriumName());
		final EventItem show = eventService.assignAuditorium(event, auditorium, getTime());
		bookingService.bookTicket(user, show, getSeat());
		bookingService.bookTicket(user, show, getSeat());
		final List<Ticket> tickets = bookingService.getTicketsForShow(show);

		assertEquals(tickets.size(), 2);

	}

}
