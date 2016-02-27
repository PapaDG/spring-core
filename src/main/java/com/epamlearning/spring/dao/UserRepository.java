package com.epamlearning.spring.dao;

import java.util.List;

import com.epamlearning.spring.domain.User;

public interface UserRepository {

	User reqister(User user);

	void remove(Long id);

	User getUserById(Long id);

	User getUserByEmail(String email);

	List<User> getUsersByName(String name);

}
