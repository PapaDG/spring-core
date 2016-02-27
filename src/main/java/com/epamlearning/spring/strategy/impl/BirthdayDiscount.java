package com.epamlearning.spring.strategy.impl;

import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.strategy.DiscountStrategy;

public class BirthdayDiscount implements DiscountStrategy {

	public int getDiscount(final User user, final EventItem show) {
		if (user.getBirthday() != null) {
			final int dayShow = show.getTime().getDayOfMonth();
			final int monthShow = show.getTime().getMonthOfYear();
			final int birthDay = user.getBirthday().getDayOfMonth();
			final int birthMonth = user.getBirthday().getMonthOfYear();

			if (dayShow == birthDay && monthShow == birthMonth) {
				return 5;
			}
		}
		return 0;
	}

}
