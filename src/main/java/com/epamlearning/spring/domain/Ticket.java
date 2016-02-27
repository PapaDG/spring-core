package com.epamlearning.spring.domain;

public class Ticket {

	private Long id;
	private User user;
	private Integer seat;
	private EventItem item;
	private Long price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public EventItem getItem() {
		return item;
	}

	public void setItem(EventItem show) {
		this.item = show;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}
