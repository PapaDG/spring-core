package com.epamlearning.spring.sevice.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epamlearning.spring.aspect.annotation.EventRequest;
import com.epamlearning.spring.dao.impl.EventItemRepositoryImpl;
import com.epamlearning.spring.dao.impl.EventRepositoryImpl;
import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Rating;
import com.epamlearning.spring.sevice.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepositoryImpl eventRepository;
	@Autowired
	private EventItemRepositoryImpl eventItemRepository;

	public Event create(final String name, final Long price, final Rating rating) {
		final Event event = generateEvent(name, price, rating);
		return eventRepository.create(event);
	}

	public void remove(final Long id) {
		eventRepository.remove(id);
	}

	@EventRequest
	public Event getByName(final String name) {
		return eventRepository.getByName(name);
	}

	public List<EventItem> getForDateRange(final DateTime from, final DateTime to) {
		return eventItemRepository.findForDateRange(from, to);
	}

	public List<EventItem> getNextEvents(final DateTime to) {
		return eventItemRepository.getNextEvents(to);
	}

	public EventItem assignAuditorium(final Event event, final Auditorium auditorium, final DateTime time)
	{
		return eventItemRepository.assign(generateShow(event, auditorium, time));
	}

	private Event generateEvent(final String name, final Long price, final Rating rating) {
		final Event event = new Event();
		event.setName(name);
		event.setPrice(price);
		event.setRating(rating);
		return event;
	}

	private EventItem generateShow(final Event event, final Auditorium auditorium, final DateTime time) {
		final EventItem show = new EventItem();
		show.setEvent(event);
		show.setAuditorium(auditorium);
		show.setTime(time);
		return show;
	}

}
