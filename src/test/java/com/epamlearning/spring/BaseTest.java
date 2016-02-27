package com.epamlearning.spring;

import org.joda.time.DateTime;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseTest {

	private static final Long COUNTER = 6L;
	private static final DateTime TIME = new DateTime(2016, 01, 01, 0, 0);
	private static final String EVENTNAME = "TestEvent1";
	private static final Long PRICE = 100L;
	private static final int seat = 5;

	@Value("${auditorium1.name}")
	private String auditoriumName;
	@Value("${auditorium1.seats}")
	private int auditoriumSeats;
	@Value("${user.name}")
	private String userName;
	@Value("${user.email}")
	private String userEmail;

	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getAuditoriumName() {
		return auditoriumName;
	}

	public int getAuditoriumSeats() {
		return auditoriumSeats;
	}

	public static int getSeat() {
		return seat;
	}

	public static Long getCounter() {
		return COUNTER;
	}

	public static DateTime getTime() {
		return TIME;
	}

	public static String getEventname() {
		return EVENTNAME;
	}

	public static Long getPrice() {
		return PRICE;
	}

}
