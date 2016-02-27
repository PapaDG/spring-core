package com.epamlearning.spring.aspects;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epamlearning.spring.BaseTest;
import com.epamlearning.spring.dao.impl.StatisticsRepositoryImpl;
import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Rating;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.AuditoriumService;
import com.epamlearning.spring.sevice.BookingService;
import com.epamlearning.spring.sevice.EventService;
import com.epamlearning.spring.sevice.UserService;

public class LuckyWinnerAspectTest extends BaseTest {

	@Autowired
	private StatisticsRepositoryImpl statRepository;
	@Autowired
	private EventService eventService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuditoriumService auditoriumService;

	@Test
	public void testSavePriceRequest() {
		String name = "Testando";
		User user = userService.register(getUserName(), getUserEmail());
		user.setBirthday(getTime());
		Event event = eventService.create(name, getCounter(), Rating.AVERAGE);
		Auditorium auditorium = auditoriumService.getAuditoriumByName(getAuditoriumName());
		EventItem show = eventService.assignAuditorium(event, auditorium, getTime());
		List<Integer> seats = new ArrayList<Integer>();
		seats.add(11);
		seats.add(5);
		for (int i = 0; i < getCounter(); i++) {
			bookingService.getTicketPrice(user, show, seats, getTime());
		}
		assertEquals(getCounter(), statRepository.getStatisticPriceById(show.getId()));
	}

}
