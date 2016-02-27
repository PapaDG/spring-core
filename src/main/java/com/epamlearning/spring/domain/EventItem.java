package com.epamlearning.spring.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class EventItem {

	private Long id;
	private Event event;
	private Auditorium auditorium;
	private DateTime time;
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
