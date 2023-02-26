package com.ameya.theaterservice.controller;

import java.util.List;

import com.ameya.theaterservice.dto.CityDto;
import com.ameya.theaterservice.dto.ScheduleDto;
import com.ameya.theaterservice.dto.TheaterDto;
import com.ameya.theaterservice.dto.TierDto;
import com.ameya.theaterservice.exception.city.NoSuchCityException;
import com.ameya.theaterservice.exception.partner.NoSuchPartnerException;
import com.ameya.theaterservice.exception.showtime.ShowtimeAlreadyExistsException;
import com.ameya.theaterservice.exception.theater.NoSuchTheaterException;
import com.ameya.theaterservice.exception.theater.TheaterAlreadyExistsException;
import com.ameya.theaterservice.exception.tier.NoSuchTierException;
import com.ameya.theaterservice.exception.tier.TierAlreadyExistsException;
import com.ameya.theaterservice.service.TheaterService;
import com.ameya.theaterservice.service.TierService;
import com.ameya.theaterservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	TheaterService theaterService;
	
	@Autowired
	TierService tierService;
	
	@Autowired
	Environment env;

//	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<TheaterDto> addTheater(@RequestBody TheaterDto dto)
			throws TheaterAlreadyExistsException, TierAlreadyExistsException, ShowtimeAlreadyExistsException,
			NoSuchTierException, NoSuchTheaterException, NoSuchCityException, NoSuchPartnerException {
		
		RestTemplate rt = new RestTemplate();
		
		rt.postForEntity(env.getProperty("add.theater.url"), dto, TheaterDto.class);

		return ResponseEntity.ok(theaterService.addTheater(dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TheaterDto> getTheater(@PathVariable int id) throws NoSuchTheaterException {

		return ResponseEntity.ok(theaterService.getTheaterById(id));
	}

	@GetMapping
	public ResponseEntity<List<TheaterDto>> getAllTheaters() {

		return ResponseEntity.ok(theaterService.getAllTheaters());
	}

//	@Secured("ROLE_ADMIN")
	@PutMapping
	public ResponseEntity<TheaterDto> updateTheater(@RequestBody TheaterDto dto) throws NoSuchTheaterException {
		return ResponseEntity.ok(theaterService.updateTheater(dto));
	}

//	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<OutputMessage> deleteTheater(@PathVariable int id) throws NoSuchTheaterException {
		return ResponseEntity.ok(theaterService.deleteTheater(id));
	}
	
	@GetMapping("/tier")
	public ResponseEntity<List<TierDto>> getTiers(){
		return ResponseEntity.ok(tierService.getAllTier());
	}
	
//	@PostMapping("/tier")
	public ResponseEntity<TierDto> addTier(@RequestBody TierDto dto) throws TierAlreadyExistsException {
		return ResponseEntity.ok(tierService.addTier(dto));
	}
	
	@PostMapping("/schedule")
	public ResponseEntity<String> addSchedule(@RequestBody List<ScheduleDto> schedules) throws NoSuchTheaterException{
		
		return ResponseEntity.ok(theaterService.addSchedules(schedules));
	}
	

}
