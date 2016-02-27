package com.epamlearning.spring.sevice;

import java.util.List;

import org.joda.time.DateTime;

import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.Rating;
import com.epamlearning.spring.domain.EventItem;

public interface EventService {
	Event create(String name, Long price, Rating rating);

	void remove(Long id);

	Event getByName(String name);

	List<EventItem> getForDateRange(DateTime from, DateTime to);

	List<EventItem> getNextEvents(DateTime to);

	EventItem assignAuditorium(Event event, Auditorium auditorium, DateTime time);
}
