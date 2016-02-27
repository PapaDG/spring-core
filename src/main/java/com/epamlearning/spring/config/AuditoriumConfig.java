package com.epamlearning.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.epamlearning.spring.domain.Auditorium;

@Configuration
@PropertySource("classpath:auditorium.propertis")
public class AuditoriumConfig {

	@Value("${auditorium1.name}")
	private String auditoriumNameFirst;
	@Value("${auditorium1.seats}")
	private int auditoriumSeatsFirst;
	@Value("#{'${auditorium1.vips}'.split(',')}")
	private List<Integer> auditoriumVipsFirst;
	@Value("${auditorium2.name}")
	private String auditoriumNameSecond;
	@Value("${auditorium2.seats}")
	private int auditoriumSeatsSecond;
	@Value("#{'${auditorium2.vips}'.split(',')}")
	private List<Integer> auditoriumVipsSecond;
	@Value("${auditorium3.name}")
	private String auditoriumNameThird;
	@Value("${auditorium3.seats}")
	private int auditoriumSeatsThird;
	@Value("#{'${auditorium3.vips}'.split(',')}")
	private List<Integer> auditoriumVipsThird;

	@Bean
	public Auditorium getFirstAuditorium() {
		return generateAuditorium(auditoriumNameFirst, auditoriumSeatsFirst, auditoriumVipsFirst);
	}

	@Bean
	public Auditorium getSecondAuditorium() {
		return generateAuditorium(auditoriumNameSecond, auditoriumSeatsSecond, auditoriumVipsSecond);
	}

	@Bean
	public Auditorium getThirdAuditorium() {
		return generateAuditorium(auditoriumNameSecond, auditoriumSeatsSecond, auditoriumVipsSecond);
	}

	@Bean
	public List<Auditorium> getAuditoriumList() {
		List<Auditorium> auditoriums = new ArrayList<Auditorium>();
		auditoriums.add(getFirstAuditorium());
		auditoriums.add(getSecondAuditorium());
		auditoriums.add(getThirdAuditorium());
		return auditoriums;
	}

	private Auditorium generateAuditorium(String name, int seats, List<Integer> vips) {
		Auditorium aud = new Auditorium();
		aud.setName(name);
		aud.setTotalSeats(seats);
		aud.setVipSeats(vips);
		return aud;
	}

}
