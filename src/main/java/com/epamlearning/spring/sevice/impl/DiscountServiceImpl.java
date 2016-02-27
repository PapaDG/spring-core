package com.epamlearning.spring.sevice.impl;

import java.util.List;

import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.DiscountService;
import com.epamlearning.spring.strategy.DiscountStrategy;

public class DiscountServiceImpl implements DiscountService {

	private final List<DiscountStrategy> strategies;

	public DiscountServiceImpl(final List<DiscountStrategy> strategies) {
		this.strategies = strategies;
	}

	public int getDiscount(final User user, final EventItem show) {
		int maxDiscount = 0;
		for (final DiscountStrategy strategy : strategies) {
			final int discount = strategy.getDiscount(user, show);
			if (discount > maxDiscount) {
				maxDiscount = discount;
			}
		}
		return maxDiscount;
	}

}
