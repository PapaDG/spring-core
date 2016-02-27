package com.epamlearning.spring.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epamlearning.spring.BaseTest;
import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.sevice.AuditoriumService;

public class AuditoriumServiceTest extends BaseTest {

	@Autowired
	private AuditoriumService service;

	@Test
	public void testGetAuditoriums() {
		final List<Auditorium> aList = service.getAuditoriums();
		assertEquals(aList.size(), 3);
	}

	@Test
	public void testGetSeatsByName() {
		final int seats = service.getSeatsByName(getAuditoriumName());
		assertEquals(seats, getAuditoriumSeats());
	}

	@Test
	public void testGetVipsByName() {
		final List<Integer> seats = service.getVipsByName(getAuditoriumName());
		assertEquals(seats.size(), 10);
	}

}
