package com.epamlearning.spring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epamlearning.spring.dao.EventRepository;
import com.epamlearning.spring.domain.Event;

@Repository
public class EventRepositoryImpl implements EventRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Event create(final Event event) {
		final String sqlInsert = "INSERT INTO events(name , price, rating) values(? , ?, ?)";
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, event.getName());
				ps.setLong(2, event.getPrice());
				ps.setString(3, event.getRating().name());
				return ps;
			}
		}, holder);
		event.setId(holder.getKey().longValue());
		return event;
	}

	public void remove(final Long id) {
		final String sqlDelete = "DELETE FROM events WHERE ID  = ?";
		jdbcTemplate.update(sqlDelete, id);
	}

	public Event getByName(final String name) {
		final String sqlSelect = "SELECT * FROM events WHERE name  = ?";
		return jdbcTemplate.queryForObject(sqlSelect, new Object[] { name },
				new BeanPropertyRowMapper<Event>(Event.class));
	}
}
