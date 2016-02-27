package com.epamlearning.spring.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.epamlearning.spring.BaseTest;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.UserService;

public class UserServiceTest extends BaseTest {

	@Autowired
	private UserService userService;

	@Test
	public void testRegister() {
		final User user = userService.register(getUserName(), getUserEmail());
		assertEquals(getUserName(), user.getName());
		assertEquals(getUserEmail(), user.getEmail());
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void testRemove() {
		final User user = userService.register(getUserName(), getUserEmail());
		userService.remove(user.getId());
		userService.getUserById(user.getId());
	}

	@Test
	public void testGetUserById() {
		final User user = userService.register(getUserName(), getUserEmail());
		final User userById = userService.getUserById(user.getId());

		assertEquals(user.getEmail(), userById.getEmail());
		assertEquals(user.getName(), userById.getName());
	}

	@Test
	public void testGetUserByName() {
		userService.register(getUserName(), getUserEmail());
		final List<User> users = userService.getUsersByName(getUserName());

		assertEquals(getUserName(), users.get(0).getName());
	}

	@Test
	public void testGetUserByEmail() {
		userService.register(getUserName(), getUserEmail());
		final User users = userService.getUserByEmail(getUserEmail());

		assertEquals(getUserEmail(), users.getEmail());
	}

}
