package com.ameya.schedulemicroservice.controller;

import java.util.List;

import com.ameya.schedulemicroservice.dto.TheaterDto;
import com.ameya.schedulemicroservice.dto.TierDto;
import com.ameya.schedulemicroservice.exception.city.NoSuchCityException;
import com.ameya.schedulemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.schedulemicroservice.exception.partner.NoSuchPartnerException;
import com.ameya.schedulemicroservice.exception.showtime.ShowtimeAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.theater.NoSuchTheaterException;
import com.ameya.schedulemicroservice.exception.theater.TheaterAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.tier.NoSuchTierException;
import com.ameya.schedulemicroservice.exception.tier.TierAlreadyExistsException;
import com.ameya.schedulemicroservice.service.TheaterService;
import com.ameya.schedulemicroservice.service.TierService;
import com.ameya.schedulemicroservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	TheaterService theaterService;
	
	@Autowired
	TierService tierService;

//	@Secured("ROLE_ADMIN")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<TheaterDto> addTheater(@RequestBody TheaterDto dto)
			throws TheaterAlreadyExistsException, TierAlreadyExistsException, ShowtimeAlreadyExistsException,
			NoSuchMovieException, NoSuchTierException, NoSuchTheaterException, NoSuchCityException, NoSuchPartnerException {

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
	
	@PostMapping("/tier")
	public ResponseEntity<TierDto> addTier(@RequestBody TierDto dto) throws TierAlreadyExistsException {
		return ResponseEntity.ok(tierService.addTier(dto));
	}
	

}
