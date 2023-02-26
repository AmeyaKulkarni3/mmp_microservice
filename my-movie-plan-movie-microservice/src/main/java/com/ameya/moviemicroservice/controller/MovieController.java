package com.ameya.moviemicroservice.controller;

import java.io.IOException;
import java.util.List;

import com.ameya.moviemicroservice.dto.LanguageDto;
import com.ameya.moviemicroservice.dto.MovieDto;
import com.ameya.moviemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.moviemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.moviemicroservice.exception.movie.MovieAlreadyExistsException;
import com.ameya.moviemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.moviemicroservice.service.MovieService;
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
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@Autowired
	Environment env;

//	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto dto)
			throws MovieAlreadyExistsException, IOException, NoSuchGenreException, NoSuchLanguageException {
		
		RestTemplate rt = new RestTemplate();
		
		rt.postForEntity(env.getProperty("add.movie.url"), dto, MovieDto.class);

		return ResponseEntity.ok(movieService.addMovie(dto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieDto> getMovieById(@PathVariable int id) throws NoSuchMovieException{
		return ResponseEntity.ok(movieService.getMovieById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<MovieDto>> getAllMovies(){
		return ResponseEntity.ok(movieService.getAllMovies());
	}
	
	@GetMapping("/admin")
	public ResponseEntity<List<MovieDto>> getAllMoviesAdmin(){
		return ResponseEntity.ok(movieService.getAllMoviesAdmin());
	}
	
//	@Secured("ROLE_ADMIN")
	@PutMapping
	public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto dto) throws NoSuchMovieException{
		return ResponseEntity.ok(movieService.updateMovie(dto));
	}
	
//	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<OutputMessage> deleteMovie(@PathVariable int id) throws NoSuchMovieException{
		return ResponseEntity.ok(movieService.deleteMovie(id));
	}
	
//	@Secured("ROLE_ADMIN")
	@PutMapping("/change-status/{id}")
	public ResponseEntity<MovieDto> updateStatus(@PathVariable int id) throws NoSuchMovieException{
		return ResponseEntity.ok(movieService.updateStatus(id));
	}

}
