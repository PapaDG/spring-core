package com.epamlearning.spring.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class User {
	private Long id;
	private String name;
	private String email;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private DateTime birthday;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public DateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(DateTime birthday) {
		this.birthday = birthday;
	}

}
