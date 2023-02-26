package com.ameya.theaterservice.service;

import java.util.List;

import com.ameya.theaterservice.dto.ScheduleDto;
import com.ameya.theaterservice.dto.TheaterDto;
import com.ameya.theaterservice.exception.city.NoSuchCityException;
import com.ameya.theaterservice.exception.partner.NoSuchPartnerException;
import com.ameya.theaterservice.exception.showtime.ShowtimeAlreadyExistsException;
import com.ameya.theaterservice.exception.theater.NoSuchTheaterException;
import com.ameya.theaterservice.exception.theater.TheaterAlreadyExistsException;
import com.ameya.theaterservice.exception.tier.NoSuchTierException;
import com.ameya.theaterservice.exception.tier.TierAlreadyExistsException;
import com.ameya.theaterservice.utils.OutputMessage;

public interface TheaterService {

	TheaterDto addTheater(TheaterDto theater)
			throws TheaterAlreadyExistsException, TierAlreadyExistsException, ShowtimeAlreadyExistsException,
			NoSuchTierException, NoSuchTheaterException, NoSuchCityException, NoSuchPartnerException;

	TheaterDto getTheaterById(int id) throws NoSuchTheaterException;

	List<TheaterDto> getAllTheaters();

	TheaterDto updateTheater(TheaterDto theaterDto) throws NoSuchTheaterException;
	
	String addSchedules(List<ScheduleDto> schedules) throws NoSuchTheaterException;

	OutputMessage deleteTheater(int id) throws NoSuchTheaterException;

}
