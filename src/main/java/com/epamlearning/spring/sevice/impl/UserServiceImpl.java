package com.epamlearning.spring.sevice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epamlearning.spring.dao.UserRepository;
import com.epamlearning.spring.domain.Ticket;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User register(final String name, final String email) {
		return userRepository.reqister(createUser(name, email));
	}

	public void remove(final Long id) {
		userRepository.remove(id);
	}

	public User getUserById(final Long id) {
		return userRepository.getUserById(id);
	}

	public List<User> getUsersByName(final String name) {
		return userRepository.getUsersByName(name);
	}

	public User getUserByEmail(final String email) {
		return userRepository.getUserByEmail(email);
	}

	public Map<Integer, Ticket> getBookedTickets(final Long id) {
		final Map<Integer, Ticket> result = new HashMap<Integer, Ticket>();
		for (final Ticket ticket : getUserById(id).getTickets()) {
			result.put(ticket.getSeat(), ticket);
		}
		return result;
	}

	private User createUser(final String name, final String email) {
		final User user = new User();
		user.setName(name);
		user.setEmail(email);
		return user;
	}

}
