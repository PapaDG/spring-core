package com.epamlearning.spring.dao;

import com.epamlearning.spring.domain.Event;

public interface EventRepository
{

	Event create(final Event event);

	void remove(Long id);

	Event getByName(String name);

}
