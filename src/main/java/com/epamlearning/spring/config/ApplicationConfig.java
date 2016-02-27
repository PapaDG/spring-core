package com.epamlearning.spring.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.epamlearning.spring.aspect.CounterAspect;
import com.epamlearning.spring.aspect.DiscountAspect;
import com.epamlearning.spring.aspect.LuckyWinnerAspect;
import com.epamlearning.spring.sevice.DiscountService;
import com.epamlearning.spring.sevice.impl.DiscountServiceImpl;
import com.epamlearning.spring.strategy.DiscountStrategy;
import com.epamlearning.spring.strategy.impl.BirthdayDiscount;
import com.epamlearning.spring.strategy.impl.BonusDiscount;

@Configuration
@ComponentScan("com.epamlearning.spring")
@EnableAspectJAutoProxy
public class ApplicationConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource dataSource() {
		final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		final EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.DERBY)
				.addScript("sql/create_common_tables.sql").addScript("sql/create_stat_tables.sql").build();
		return db;
	}

	@Bean
	public DiscountAspect getDiscountAspect() {
		return new DiscountAspect();
	}

	@Bean
	public CounterAspect getCounterAspect() {
		return new CounterAspect();
	}

	@Bean
	public LuckyWinnerAspect getLuckyWinnerAspect() {
		return new LuckyWinnerAspect();
	}

	@Bean
	public DiscountService getDiscountService() {
		final List<DiscountStrategy> list = new ArrayList<DiscountStrategy>();
		list.add(new BirthdayDiscount());
		list.add(new BonusDiscount());
		return new DiscountServiceImpl(list);
	}
}
