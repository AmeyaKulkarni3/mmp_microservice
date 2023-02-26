package com.ameya.moviemicroservice.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ameya.moviemicroservice.dto.GenreDto;
import com.ameya.moviemicroservice.dto.LanguageDto;
import com.ameya.moviemicroservice.dto.MovieDto;
import com.ameya.moviemicroservice.entity.Genre;
import com.ameya.moviemicroservice.entity.Language;
import com.ameya.moviemicroservice.entity.Movie;
import com.ameya.moviemicroservice.exception.ExceptionConstants;
import com.ameya.moviemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.moviemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.moviemicroservice.exception.movie.MovieAlreadyExistsException;
import com.ameya.moviemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.moviemicroservice.repository.GenreRepository;
import com.ameya.moviemicroservice.repository.LanguageRepository;
import com.ameya.moviemicroservice.repository.MovieRepository;
import com.ameya.moviemicroservice.service.MovieService;
import com.ameya.moviemicroservice.utils.CrudMessage;
import com.ameya.moviemicroservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:MovieValidationMessages.properties")
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	LanguageRepository languageRepository;

	@Autowired
	Environment env;

	@Override
	public MovieDto addMovie(MovieDto dto)
			throws MovieAlreadyExistsException, IOException, NoSuchGenreException, NoSuchLanguageException {

		Movie savedMovie = movieRepository.findByName(dto.getName());
		if (savedMovie != null) {
			throw new MovieAlreadyExistsException(env.getProperty(ExceptionConstants.MOVIE_ALREADY_EXISTS.toString()));
		}

		Movie movie = new Movie();
		movie.setName(dto.getName());
		movie.setDirectors(dto.getDirectors());
		movie.setCast(dto.getCast());
		movie.setReleaseDate(dto.getReleaseDate());
		movie.setDuration(dto.getDuration());
		movie.setPoster("");

		List<GenreDto> gdtos = dto.getGenres();
		List<Genre> genres = new ArrayList<>();
		for (GenreDto gdto : gdtos) {
			Genre g = genreRepository.findById(gdto.getId()).orElseThrow(
					() -> new NoSuchGenreException(env.getProperty(ExceptionConstants.GENRE_NOT_FOUND.toString())));
			genres.add(g);
		}
		movie.setGenres(genres);

		List<LanguageDto> ldtos = dto.getLanguages();
		List<Language> languages = new ArrayList<>();
		for (LanguageDto ldto : ldtos) {
			Language l = languageRepository.findById(ldto.getId()).orElseThrow(() -> new NoSuchLanguageException(
					env.getProperty(ExceptionConstants.LANGUAGE_NOT_FOUND.toString())));
			languages.add(l);
		}
		movie.setLanguages(languages);

		LocalDate today = LocalDate.now();

		if (dto.getReleaseDate().isBefore(today)) {
			movie.setActive(true);
		} else {
			movie.setActive(false);
		}

		Movie saved = movieRepository.save(movie);

		MovieDto returnValue = dataTransfer(saved);

		return returnValue;
	}

	private MovieDto dataTransfer(Movie movie) {
		MovieDto dto = new MovieDto();
		dto.setId(movie.getId());
		dto.setName(movie.getName());
		dto.setDirectors(movie.getDirectors());
		dto.setCast(movie.getCast());
		dto.setDuration(movie.getDuration());
		dto.setReleaseDate(movie.getReleaseDate());
		dto.setActive(movie.isActive());
		dto.setPoster(movie.getPoster());
		List<Genre> genres = movie.getGenres();
		List<GenreDto> gdtos = new ArrayList<>();
		for (Genre g : genres) {
			GenreDto gdto = new GenreDto();
			gdto.setId(g.getId());
			gdto.setName(g.getName());
			gdtos.add(gdto);
		}
		List<Language> languages = movie.getLanguages();
		List<LanguageDto> ldtos = new ArrayList<>();
		for (Language l : languages) {
			LanguageDto ldto = new LanguageDto();
			ldto.setId(l.getId());
			ldto.setName(l.getName());
			ldtos.add(ldto);
		}
		dto.setGenres(gdtos);
		dto.setLanguages(ldtos);
		return dto;
	}

	@Override
	public MovieDto getMovieById(int id) throws NoSuchMovieException {

		Movie movie = movieRepository.findById(id).orElseThrow(
				() -> new NoSuchMovieException(env.getProperty(ExceptionConstants.MOVIE_NOT_FOUND.toString())));
		MovieDto mdto = dataTransfer(movie);
		return mdto;
	}

	@Override
	public List<MovieDto> getAllMovies() {

		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		List<MovieDto> movieDtos = new ArrayList<>();
		for (Movie m : movies) {
			if (m.isActive()) {
				MovieDto mdto = dataTransfer(m);
				movieDtos.add(mdto);
			}
		}
		return movieDtos;
	}

	@Override
	public List<MovieDto> getAllMoviesAdmin() {

		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		List<MovieDto> movieDtos = new ArrayList<>();
		for (Movie m : movies) {
			MovieDto mdto = dataTransfer(m);
			movieDtos.add(mdto);
		}
		return movieDtos;

	}

	@Override
	public MovieDto updateMovie(MovieDto dto) throws NoSuchMovieException {
		Movie movie = movieRepository.findById(dto.getId()).orElseThrow(
				() -> new NoSuchMovieException(env.getProperty(ExceptionConstants.MOVIE_NOT_FOUND.toString())));
		if (dto.getName() != null && !dto.getName().equals(movie.getName())) {
			movie.setName(dto.getName());
		}
		if (dto.getDirectors() != null && !dto.getDirectors().equals(movie.getDirectors())) {
			movie.setDirectors(dto.getDirectors());
		}
		if (dto.getCast() != null && !dto.getCast().equals(movie.getCast())) {
			movie.setCast(dto.getCast());
		}
		if (dto.getDuration() != null && !dto.getDuration().equals(movie.getDuration())) {
			movie.setDuration(dto.getDuration());
		}
		if (dto.getReleaseDate() != null && !dto.getReleaseDate().equals(movie.getReleaseDate())) {
			movie.setReleaseDate(dto.getReleaseDate());
		}
		if (dto.getPoster() != null && !dto.getPoster().equals(movie.getPoster())) {
			movie.setPoster(dto.getPoster());
		}
		Movie saved = movieRepository.save(movie);

		return dataTransfer(saved);
	}

	@Override
	public OutputMessage deleteMovie(int id) throws NoSuchMovieException{

		Movie movie = movieRepository.findById(id).orElseThrow(
				() -> new NoSuchMovieException(env.getProperty(ExceptionConstants.MOVIE_NOT_FOUND.toString())));

		movieRepository.delete(movie);

		OutputMessage om = new OutputMessage();
		om.setMessage(env.getProperty(CrudMessage.MOVIE_DELETE_SUCCESS.toString()));

		return om;
	}

	@Override
	public MovieDto updateStatus(int id) throws NoSuchMovieException{
		Movie movie = movieRepository.findById(id).orElseThrow(
				() -> new NoSuchMovieException(env.getProperty(ExceptionConstants.MOVIE_NOT_FOUND.toString())));
		movie.setActive(!movie.isActive());
		Movie saved = movieRepository.save(movie);
		MovieDto returnValue = dataTransfer(saved);
		return returnValue;
	}

}
