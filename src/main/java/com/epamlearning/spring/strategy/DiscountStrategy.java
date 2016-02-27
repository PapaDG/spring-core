package com.epamlearning.spring.strategy;

import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.User;

public interface DiscountStrategy {
	int getDiscount(User user, EventItem show);
}
