package com.ameya.schedulemicroservice.controller;

import java.util.List;

import com.ameya.schedulemicroservice.dto.GenreDto;
import com.ameya.schedulemicroservice.exception.genre.GenreAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.schedulemicroservice.service.GenreService;
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
@RequestMapping("/genre")
public class GenreController {

	@Autowired
	GenreService genreService;

//	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<GenreDto> addGenre(@RequestBody GenreDto genreDto) throws GenreAlreadyExistsException {

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
