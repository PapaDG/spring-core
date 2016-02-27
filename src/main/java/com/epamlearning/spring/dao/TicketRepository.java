package com.epamlearning.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Ticket;

@Repository
public interface TicketRepository {

	Ticket bookTicket(Ticket ticket);

	List<Ticket> getTicketsForShow(EventItem show);
}
