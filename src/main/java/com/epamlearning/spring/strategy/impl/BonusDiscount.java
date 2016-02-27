package com.epamlearning.spring.strategy.impl;

import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.strategy.DiscountStrategy;

public class BonusDiscount implements DiscountStrategy {

	public int getDiscount(final User user, final EventItem show) {
		final int ticketNum = user.getTickets().size();
		if (ticketNum > 0 && ticketNum % 10 == 0) {
			return 50;
		} else {
			return 0;
		}
	}
}
