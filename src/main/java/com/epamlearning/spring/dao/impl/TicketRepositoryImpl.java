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

import com.epamlearning.spring.dao.TicketRepository;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Ticket;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Ticket bookTicket(final Ticket ticket) {
		final String sqlInsert = "INSERT INTO tickets(userid , seat, eventitemid) values(? , ?, ?)";
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, ticket.getUser().getId());
				ps.setLong(2, ticket.getSeat());
				ps.setLong(3, ticket.getItem().getEvent().getId());
				return ps;
			}
		}, holder);
		ticket.setId(holder.getKey().longValue());
		return ticket;
	}

	public List<Ticket> getTicketsForShow(final EventItem show) {
		final String sqlSelect = "SELECT * FROM tickets WHERE eventitemid  = ?";
		return jdbcTemplate.query(sqlSelect, new Object[] { show.getId() }, new BeanPropertyRowMapper<Ticket>(Ticket.class));
	}

}
