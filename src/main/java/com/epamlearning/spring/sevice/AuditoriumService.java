package com.epamlearning.spring.sevice;

import java.util.List;

import com.epamlearning.spring.domain.Auditorium;

public interface AuditoriumService {
	List<Auditorium> getAuditoriums();

	int getSeatsByName(String name);

	List<Integer> getVipsByName(String name);

	Auditorium getAuditoriumByName(String auditoriumName);
}
