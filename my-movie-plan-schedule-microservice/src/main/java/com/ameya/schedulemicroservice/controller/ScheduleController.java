package com.ameya.schedulemicroservice.controller;

import java.util.List;

import com.ameya.schedulemicroservice.dto.ScheduleDto;
import com.ameya.schedulemicroservice.exception.movie.MovieNotActiveException;
import com.ameya.schedulemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.schedulemicroservice.exception.schedule.CantScheduleShowException;
import com.ameya.schedulemicroservice.exception.schedule.NoSuchScheduleException;
import com.ameya.schedulemicroservice.exception.schedule.ScheduleAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.showtime.NoSuchShowtimeException;
import com.ameya.schedulemicroservice.exception.theater.NoSuchTheaterException;
import com.ameya.schedulemicroservice.model.request.CreateScheduleRequestModel;
import com.ameya.schedulemicroservice.model.request.UpdateScheduleRequestModel;
import com.ameya.schedulemicroservice.model.request.UpdateSeatStatusModel;
import com.ameya.schedulemicroservice.service.ScheduleService;
import com.ameya.schedulemicroservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	Environment env;

	@PostMapping
	public ResponseEntity<List<ScheduleDto>> createSchedule(@RequestBody CreateScheduleRequestModel createSchedule)
			throws ScheduleAlreadyExistsException, NoSuchMovieException, NoSuchTheaterException,
			NoSuchShowtimeException, CantScheduleShowException, MovieNotActiveException {
		List<ScheduleDto> savedSchedules = 
				scheduleService.createSchedule(createSchedule.getMovieId(),
						createSchedule.getTheaterId(), createSchedule.getShowtimeId(), createSchedule.getDate(), createSchedule.getToDate());
		
		RestTemplate rt = new RestTemplate();
		rt.postForEntity(env.getProperty("add.schedule.url"), savedSchedules, String.class);
		
		return ResponseEntity.ok(savedSchedules);
	}
	
	@GetMapping
	public ResponseEntity<List<ScheduleDto>> getAllSchedules(){
		return ResponseEntity.ok(scheduleService.getAllSChedules());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable int id) throws NoSuchScheduleException {
		return ResponseEntity.ok(scheduleService.getScheduleById(id));
	}

	@GetMapping("/movie/{movieId}")
	public ResponseEntity<List<ScheduleDto>> getScheduleByMovie(@PathVariable int movieId) {
		return ResponseEntity.ok(scheduleService.getScheduleByMovie(movieId));
	}

	@GetMapping("/theater/{theaterId}")
	public ResponseEntity<List<ScheduleDto>> getScheduleByTheater(@PathVariable int theaterId) {
		return ResponseEntity.ok(scheduleService.getScheduleByTheater(theaterId));
	}

	@GetMapping("/showtime/{showtimeId}")
	public ResponseEntity<List<ScheduleDto>> getScheduleByShowtime(@PathVariable int showtimeId) {
		return ResponseEntity.ok(scheduleService.getScheduleByShowtime(showtimeId));
	}

	@PutMapping
	public ResponseEntity<ScheduleDto> updateSchedule(@RequestBody UpdateScheduleRequestModel updateRequest)
			throws NoSuchScheduleException, NoSuchMovieException, NoSuchShowtimeException, NoSuchTheaterException {
		return ResponseEntity.ok(scheduleService.updateSchedule(updateRequest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<OutputMessage> deleteSchedule(@PathVariable int id) throws NoSuchScheduleException{
		return ResponseEntity.ok(scheduleService.deleteSchedule(id));
	}
	
	@PutMapping(path="/update-seat-status",consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updateSeatStatus(@RequestBody UpdateSeatStatusModel seatStatus) throws NoSuchScheduleException{
		return ResponseEntity.ok(scheduleService.updateSeatStatus(seatStatus));
	}

}
