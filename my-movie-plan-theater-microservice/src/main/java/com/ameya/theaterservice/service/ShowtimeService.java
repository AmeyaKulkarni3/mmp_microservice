package com.ameya.theaterservice.service;

import java.util.List;

import com.ameya.theaterservice.dto.ShowtimeDto;
import com.ameya.theaterservice.exception.showtime.NoSuchShowtimeException;
import com.ameya.theaterservice.exception.showtime.ShowtimeAlreadyExistsException;

public interface ShowtimeService {
	
	ShowtimeDto addShowtime(ShowtimeDto dto) throws ShowtimeAlreadyExistsException;
	
	ShowtimeDto getShowtimeById(int id) throws NoSuchShowtimeException;
	
	List<ShowtimeDto> getAllShowtimes();
	
	ShowtimeDto updateShowtime(ShowtimeDto dto) throws NoSuchShowtimeException;
	
	String deleteShowtime(int id) throws NoSuchShowtimeException;

}
