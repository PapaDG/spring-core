package com.epamlearning.spring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epamlearning.spring.dao.UserRepository;
import com.epamlearning.spring.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final String EMAIL = "email";
	private static final String ID = "id";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User reqister(final User user) {
		final String sqlInsert = "INSERT INTO users(name , email) values(? , ?)";
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getEmail());
				return ps;
			}
		}, holder);
		user.setId(holder.getKey().longValue());
		return user;
	}

	public void remove(final Long id) {
		final String sqlDelete = "DELETE FROM users WHERE ID  = ?";
		jdbcTemplate.update(sqlDelete, id);
	}

	public User getUserById(final Long id) {
		return getUserByParam(ID, id);
	}

	public User getUserByEmail(final String email) {
		return getUserByParam(EMAIL, email);
	}

	public List<User> getUsersByName(final String name) {
		final String sqlDelete = "SELECT * FROM users WHERE name = ?";
		return jdbcTemplate.query(sqlDelete, new Object[] { name }, new BeanPropertyRowMapper<User>(User.class));
	}

	private User getUserByParam(final String paramName, final Object paramValue) {
		final String sqlDelete = String.format("SELECT * FROM users WHERE %s = ?", paramName);
		return jdbcTemplate.queryForObject(sqlDelete, new Object[] { paramValue },
				new BeanPropertyRowMapper<User>(User.class));
	}

}
