package com.epamlearning.spring.sevice;

import java.util.List;
import java.util.Map;

import com.epamlearning.spring.domain.Ticket;
import com.epamlearning.spring.domain.User;


public interface UserService
{
	User register(String name, String email);

	void remove(Long id);

	User getUserById(Long id);

	List<User> getUsersByName(String name);

	User getUserByEmail(String email);

	Map<Integer, Ticket> getBookedTickets(Long id);
}
