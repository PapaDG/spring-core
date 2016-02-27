package com.epamlearning.spring.dao;

import com.epamlearning.spring.domain.User;

public interface StatisticRepository {

	void saveBookingRequest(Long id);

	void saveEventRequest(Long id);

	void savePriceRequest(Long id);

	void saveDiscountRequest(String discountType, User user);

}
