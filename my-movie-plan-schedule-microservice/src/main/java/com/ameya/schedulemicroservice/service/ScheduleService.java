package com.ameya.schedulemicroservice.service;

import java.time.LocalDate;
import java.util.List;

import com.ameya.schedulemicroservice.dto.ScheduleDto;
import com.ameya.schedulemicroservice.exception.movie.MovieNotActiveException;
import com.ameya.schedulemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.schedulemicroservice.exception.schedule.CantScheduleShowException;
import com.ameya.schedulemicroservice.exception.schedule.NoSuchScheduleException;
import com.ameya.schedulemicroservice.exception.schedule.ScheduleAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.showtime.NoSuchShowtimeException;
import com.ameya.schedulemicroservice.exception.theater.NoSuchTheaterException;
import com.ameya.schedulemicroservice.model.request.UpdateScheduleRequestModel;
import com.ameya.schedulemicroservice.model.request.UpdateSeatStatusModel;
import com.ameya.schedulemicroservice.utils.OutputMessage;

public interface ScheduleService {

	List<ScheduleDto> createSchedule(int movieId, int theaterId, int showtimeId, LocalDate date, LocalDate toDate) throws ScheduleAlreadyExistsException,
			NoSuchMovieException, NoSuchTheaterException, NoSuchShowtimeException, CantScheduleShowException, MovieNotActiveException;

	ScheduleDto getScheduleById(int id) throws NoSuchScheduleException;

	List<ScheduleDto> getScheduleByMovie(int movieId);

	List<ScheduleDto> getScheduleByTheater(int theaterId);

	List<ScheduleDto> getScheduleByShowtime(int showtimeId);

	ScheduleDto updateSchedule(UpdateScheduleRequestModel dto)
			throws NoSuchScheduleException, NoSuchMovieException, NoSuchShowtimeException, NoSuchTheaterException;
	
	OutputMessage deleteSchedule(int id) throws NoSuchScheduleException;

	List<ScheduleDto> getAllSChedules();

	String updateSeatStatus(UpdateSeatStatusModel seatStatus) throws NoSuchScheduleException;
	

}
