package com.epamlearning.spring.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epamlearning.spring.dao.StatisticRepository;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.strategy.impl.BirthdayDiscount;
import com.epamlearning.spring.strategy.impl.BonusDiscount;

@Repository
public class StatisticsRepositoryImpl implements StatisticRepository {

	private static final String EVENTNAMETABLE = "eventnamestat";
	private static final String BOOKINGTABLE = "bookticketstat";
	private static final String PRICETABLE = "pricetable";
	private static final String BONUSDISCOUNTTABLE = "bonusdiscounttable";
	private static final String BIRTHDAYDISCOUNTTABLE = "birthdaydiscounttable";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private long checkIfEmpty(final String tableName, final long id) {
		String sqlSelect = String.format("SELECT COUNT(*) FROM %s WHERE id = ?", tableName);
		return jdbcTemplate.queryForObject(sqlSelect, new Object[] { id }, Long.class);
	}

	private long getStatistic(final String tableName, final long id) {
		if (checkIfEmpty(tableName, id) == 0L) {
			return 0L;
		}
		String sqlSelect = String.format("SELECT statcounter FROM %s WHERE id = ?", tableName);
		return jdbcTemplate.queryForObject(sqlSelect, new Object[] { id }, Long.class);
	}

	private void saveStatistic(final String tableName, final long id) {
		String sqlUpdate;
		long counter = getStatistic(tableName, id);

		if (counter == 0) {
			sqlUpdate = String.format("INSERT INTO %s (statcounter, id) VALUES (?,?)", tableName);
		} else {
			sqlUpdate = String.format("UPDATE %s SET statcounter = ? WHERE id = ?", tableName);
		}
		jdbcTemplate.update(sqlUpdate.toString(), new Object[] { ++counter, id });
	}

	public Long getStatisticEventById(final long id) {
		return getStatistic(EVENTNAMETABLE, id);
	}

	public void saveEventRequest(final Long id) {
		saveStatistic(EVENTNAMETABLE, id);
	}

	public Long getStatisticBookingById(final long id) {
		return getStatistic(BOOKINGTABLE, id);
	}

	public void saveBookingRequest(final Long id) {
		saveStatistic(BOOKINGTABLE, id);
	}

	public Long getStatisticPriceById(final Long id) {
		return getStatistic(PRICETABLE, id);
	}

	public void savePriceRequest(Long id) {
		saveStatistic(PRICETABLE, id);
	}

	public Long getStatisticBonusDiscountById(final Long id) {
		return getStatistic(BONUSDISCOUNTTABLE, id);
	}

	public void saveDiscountRequest(String typeDiscount, User user) {
		if (user.getId() != null) {
			if (typeDiscount.equals(BonusDiscount.class.getSimpleName())) {
				saveStatistic(BONUSDISCOUNTTABLE, user.getId());
			} else if (typeDiscount.equals(BirthdayDiscount.class.getSimpleName())) {
				saveStatistic(BIRTHDAYDISCOUNTTABLE, user.getId());
			}
		}
	}

	public Long getStatisticBirthdayDiscountById(final Long id) {
		return getStatistic(BIRTHDAYDISCOUNTTABLE, id);
	}
}
