package com.epamlearning.spring.domain;

import java.util.List;

public class Auditorium {
	private String name;
	private Integer totalSeats;
	private List<Integer> vipSeats;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public List<Integer> getVipSeats() {
		return vipSeats;
	}

	public void setVipSeats(final List<Integer> vipSeats) {
		this.vipSeats = vipSeats;
	}

}
