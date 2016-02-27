package com.epamlearning.spring.sevice;

import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.User;

public interface DiscountService {
	int getDiscount(User user, EventItem show);
}
