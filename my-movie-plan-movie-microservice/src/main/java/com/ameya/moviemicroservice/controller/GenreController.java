package com.ameya.moviemicroservice.controller;

import java.util.List;

import com.ameya.moviemicroservice.dto.GenreDto;
import com.ameya.moviemicroservice.exception.genre.GenreAlreadyExistsException;
import com.ameya.moviemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.moviemicroservice.service.GenreService;
import com.ameya.moviemicroservice.utils.OutputMessage;

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
@RequestMapping("/genre")
public class GenreController {

	@Autowired
	GenreService genreService;
	
	@Autowired
	Environment env;

//	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<GenreDto> addGenre(@RequestBody GenreDto genreDto) throws GenreAlreadyExistsException {
		
		RestTemplate rt = new RestTemplate();
		
		rt.postForEntity(env.getProperty("add.genre.url"), genreDto, GenreDto.class);

		return ResponseEntity.ok(genreService.addGenre(genreDto));

	}

	@GetMapping("/{id}")
	public ResponseEntity<GenreDto> getGenreById(@PathVariable int id) throws NoSuchGenreException {

		return ResponseEntity.ok(genreService.getGenreById(id));
	}

	@GetMapping
	public ResponseEntity<List<GenreDto>> getAllGenres() {

		return ResponseEntity.ok(genreService.getAllGenres());
	}

//	@Secured("ROLE_ADMIN")
	@PutMapping
	public ResponseEntity<GenreDto> updateGenre(@RequestBody GenreDto genreDto) throws NoSuchGenreException {
		return ResponseEntity.ok(genreService.updateGenre(genreDto));
	}

//	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<OutputMessage> deleteGenre(@PathVariable int id) throws NoSuchGenreException {
		return ResponseEntity.ok(genreService.deleteGenre(id));
	}

}
