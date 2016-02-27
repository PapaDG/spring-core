package com.epamlearning.spring.sevice.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.sevice.AuditoriumService;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
	private List<Auditorium> auditoriums;

	public void setAuditoriums(final List<Auditorium> auditoriums) {
		this.auditoriums = auditoriums;
	}

	public List<Auditorium> getAuditoriums() {
		return this.auditoriums;
	}

	public void init() {

	}

	public int getSeatsByName(final String name) {
		return getAuditoriumByName(name).getTotalSeats();
	}

	public List<Integer> getVipsByName(final String name) {
		return getAuditoriumByName(name).getVipSeats();
	}

	public Auditorium getAuditoriumByName(final String name) {
		for (final Auditorium auditorium : auditoriums) {
			if (name.equals(auditorium.getName())) {
				return auditorium;
			}
		}
		throw new NoSuchElementException("No such auditorium");
	}

}
