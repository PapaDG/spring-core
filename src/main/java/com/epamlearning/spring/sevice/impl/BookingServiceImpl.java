package com.epamlearning.spring.sevice.impl;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epamlearning.spring.aspect.annotation.BookingRequest;
import com.epamlearning.spring.aspect.annotation.PriceRequest;
import com.epamlearning.spring.dao.TicketRepository;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Ticket;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.BookingService;
import com.epamlearning.spring.sevice.DiscountService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private DiscountService discountService;
	@Autowired
	private TicketRepository ticketRepository;

	public List<Ticket> getTicketsForShow(final EventItem show) {
		return ticketRepository.getTicketsForShow(show);
	}

	@PriceRequest
	public Long getTicketPrice(final User user, final EventItem show, final List<Integer> seats,
			final DateTime dateTime) {
		final Long priceClean = show.getEvent().getPrice();
		final List<Integer> vips = show.getAuditorium().getVipSeats();
		Long priceFull = 0L;
		for (final int seat : seats) {
			if (vips.contains(seat)) {
				priceFull += priceClean * 2;
			} else {
				priceFull += priceClean;
			}
		}
		final int discount = discountService.getDiscount(user, show);
		priceFull = (priceFull * (100 - discount)) / 100;
		return priceFull;
	}

	@BookingRequest
	public Ticket bookTicket(final User user, final EventItem show, final int seat) {
		if (user == null) {
			throw new NoSuchElementException("user not registered");
		} else {
			final Ticket ticket = generateTicket(user, show, seat);
			show.getTickets().add(ticket);
			user.getTickets().add(ticket);
			ticketRepository.bookTicket(ticket);
			return ticket;
		}
	}

	private Ticket generateTicket(final User user, final EventItem show, final int seat) {
		final Ticket ticket = new Ticket();
		ticket.setSeat(seat);
		ticket.setItem(show);
		ticket.setUser(user);
		ticket.setPrice(getTicketPrice(user, show, Arrays.asList(seat), new DateTime()));
		return ticket;
	}

}
