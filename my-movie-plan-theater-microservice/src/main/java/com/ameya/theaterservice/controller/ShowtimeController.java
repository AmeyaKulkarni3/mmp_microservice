package com.ameya.theaterservice.controller;

import java.util.List;

import com.ameya.theaterservice.dto.CityDto;
import com.ameya.theaterservice.dto.ShowtimeDto;
import com.ameya.theaterservice.exception.showtime.NoSuchShowtimeException;
import com.ameya.theaterservice.exception.showtime.ShowtimeAlreadyExistsException;
import com.ameya.theaterservice.service.ShowtimeService;

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
@RequestMapping("/showtime")
public class ShowtimeController {

	@Autowired
	ShowtimeService showtimeService;
	
	@Autowired
	Environment env;

//	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<ShowtimeDto> addShowtime(@RequestBody ShowtimeDto showtimeDto)
			throws ShowtimeAlreadyExistsException {
		
		RestTemplate rt = new RestTemplate();
		
		rt.postForEntity(env.getProperty("add.showtime.url"), showtimeDto, ShowtimeDto.class);

		return ResponseEntity.ok(showtimeService.addShowtime(showtimeDto));

	}

	@GetMapping("/{id}")
	public ResponseEntity<ShowtimeDto> getShowtimeById(@PathVariable int id) throws NoSuchShowtimeException {

		return ResponseEntity.ok(showtimeService.getShowtimeById(id));
	}

	@GetMapping
	public ResponseEntity<List<ShowtimeDto>> getAllShowtimes() {

		return ResponseEntity.ok(showtimeService.getAllShowtimes());
	}

//	@Secured("ROLE_ADMIN")
	@PutMapping
	public ResponseEntity<ShowtimeDto> updateShowtime(@RequestBody ShowtimeDto showtimeDto)
			throws NoSuchShowtimeException {
		return ResponseEntity.ok(showtimeService.updateShowtime(showtimeDto));
	}

//	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShowtime(@PathVariable int id) throws NoSuchShowtimeException {
		return ResponseEntity.ok(showtimeService.deleteShowtime(id));
	}

}
