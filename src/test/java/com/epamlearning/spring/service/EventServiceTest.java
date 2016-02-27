package com.epamlearning.spring.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.epamlearning.spring.BaseTest;
import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Rating;
import com.epamlearning.spring.sevice.AuditoriumService;
import com.epamlearning.spring.sevice.EventService;
import com.epamlearning.spring.sevice.UserService;

public class EventServiceTest extends BaseTest {

	@Autowired
	private EventService eventService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuditoriumService auditoriumService;

	@Test
	public void testCreateEvent() {
		String name = "TestEvnt";
		final Event event = eventService.create(name, getPrice(), Rating.AVERAGE);
		final Event eventNew = eventService.getByName(name);
		assertEquals(event.getId(), eventNew.getId());
	}

	@Test
	public void testAssignAuditorium() {
		final Auditorium auditorium = auditoriumService.getAuditoriumByName(getAuditoriumName());
		final Event event = eventService.create(getEventname(), getPrice(), Rating.AVERAGE);
		final EventItem eventItem = eventService.assignAuditorium(event, auditorium, getTime());
		assertEquals(event, eventItem.getEvent());
		assertEquals(getTime(), eventItem.getTime());
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void testRemove() {
		final String name = "TestShow2";
		userService.register(getUserName(), getUserEmail());
		final Event event = eventService.create(name, getPrice(), Rating.AVERAGE);
		eventService.remove(event.getId());
		eventService.getByName(name);
	}

	@Test
	public void testGetByName() {
		final String name = "TestShow3";
		final Event event = eventService.create(name, getPrice(), Rating.AVERAGE);
		final Event eventByName = eventService.getByName(name);

		assertEquals(eventByName.getId(), event.getId());
		assertEquals(eventByName.getName(), event.getName());
		assertEquals(eventByName.getPrice(), event.getPrice());
		assertEquals(eventByName.getRating(), event.getRating());

	}

}
