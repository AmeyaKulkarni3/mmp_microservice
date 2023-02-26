package com.ameya.schedulemicroservice.service;

import java.io.IOException;
import java.util.List;

import com.ameya.schedulemicroservice.dto.MovieDto;
import com.ameya.schedulemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.schedulemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.schedulemicroservice.exception.movie.MovieAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.schedulemicroservice.exception.schedule.NoSuchScheduleException;
import com.ameya.schedulemicroservice.utils.OutputMessage;

public interface MovieService {

	MovieDto addMovie(MovieDto mdto)
			throws MovieAlreadyExistsException, IOException, NoSuchGenreException, NoSuchLanguageException;

	MovieDto getMovieById(int id) throws NoSuchMovieException;

	List<MovieDto> getAllMovies();

	MovieDto updateMovie(MovieDto dto) throws NoSuchMovieException;

	OutputMessage deleteMovie(int id) throws NoSuchMovieException, NoSuchScheduleException;

	MovieDto updateStatus(int id) throws NoSuchMovieException, NoSuchScheduleException;

	List<MovieDto> getAllMoviesAdmin();

}
