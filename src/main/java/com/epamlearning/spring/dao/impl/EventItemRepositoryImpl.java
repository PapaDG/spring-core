package com.epamlearning.spring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epamlearning.spring.dao.EventItemRepository;
import com.epamlearning.spring.domain.EventItem;

@Repository
public class EventItemRepositoryImpl implements EventItemRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public EventItem assign(final EventItem eventItem) {
		final String sqlInsert = "INSERT INTO eventitems(eventid , auditoriumid, datetime) values(? , ?, ?)";
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, eventItem.getEvent().getId());
				ps.setString(2, eventItem.getAuditorium().getName());
				ps.setLong(3, eventItem.getTime().getMillis());
				return ps;
			}
		}, holder);
		eventItem.setId(holder.getKey().longValue());
		return eventItem;
	}

	// TODO finish optional methods implementation
	public List<EventItem> findForDateRange(final DateTime from, final DateTime to) {
		final List<EventItem> result = new ArrayList<EventItem>();
		return result;
	}

	public List<EventItem> getNextEvents(final DateTime to) {
		final List<EventItem> result = new ArrayList<EventItem>();
		return result;
	}

}
