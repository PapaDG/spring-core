package com.epamlearning.spring.sevice;

import java.util.List;

import org.joda.time.DateTime;

import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Ticket;
import com.epamlearning.spring.domain.User;

public interface BookingService {
	Long getTicketPrice(User user, EventItem show, List<Integer> seats, DateTime dateTime);

	Ticket bookTicket(User user, EventItem show, int seat);

	List<Ticket> getTicketsForShow(EventItem show);

}
