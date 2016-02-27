package com.epamlearning.spring.dao;

import java.util.List;

import org.joda.time.DateTime;

import com.epamlearning.spring.domain.EventItem;

public interface EventItemRepository {

	List<EventItem> findForDateRange(final DateTime from, final DateTime to);

	List<EventItem> getNextEvents(final DateTime to);

	EventItem assign(final EventItem eventItem);

}
