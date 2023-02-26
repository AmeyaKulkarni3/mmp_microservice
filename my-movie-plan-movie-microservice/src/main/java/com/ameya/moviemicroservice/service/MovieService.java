package com.ameya.moviemicroservice.service;

import java.io.IOException;
import java.util.List;

import com.ameya.moviemicroservice.dto.MovieDto;
import com.ameya.moviemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.moviemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.moviemicroservice.exception.movie.MovieAlreadyExistsException;
import com.ameya.moviemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.moviemicroservice.utils.OutputMessage;

public interface MovieService {

	MovieDto addMovie(MovieDto mdto)
			throws MovieAlreadyExistsException, IOException, NoSuchGenreException, NoSuchLanguageException;

	MovieDto getMovieById(int id) throws NoSuchMovieException;

	List<MovieDto> getAllMovies();

	MovieDto updateMovie(MovieDto dto) throws NoSuchMovieException;

	OutputMessage deleteMovie(int id) throws NoSuchMovieException;

	MovieDto updateStatus(int id) throws NoSuchMovieException;

	List<MovieDto> getAllMoviesAdmin();

}
