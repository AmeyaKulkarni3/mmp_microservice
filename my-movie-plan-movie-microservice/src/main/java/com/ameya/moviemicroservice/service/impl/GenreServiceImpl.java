package com.ameya.moviemicroservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ameya.moviemicroservice.dto.GenreDto;
import com.ameya.moviemicroservice.dto.LanguageDto;
import com.ameya.moviemicroservice.dto.MovieDto;
import com.ameya.moviemicroservice.entity.Genre;
import com.ameya.moviemicroservice.entity.Language;
import com.ameya.moviemicroservice.entity.Movie;
import com.ameya.moviemicroservice.exception.ExceptionConstants;
import com.ameya.moviemicroservice.exception.genre.GenreAlreadyExistsException;
import com.ameya.moviemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.moviemicroservice.repository.GenreRepository;
import com.ameya.moviemicroservice.service.GenreService;
import com.ameya.moviemicroservice.utils.CrudMessage;
import com.ameya.moviemicroservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:GenreValidationMessages.properties")
@Transactional
public class GenreServiceImpl implements GenreService {

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	Environment env;

	@Override
	public GenreDto addGenre(GenreDto dto) throws GenreAlreadyExistsException {

		Genre genre = genreRepository.findByName(dto.getName());

		if (genre != null) {
			throw new GenreAlreadyExistsException(env.getProperty(ExceptionConstants.GENRE_ALREADY_EXISTS.toString()));
		}

		Genre g = new Genre();

		g.setName(dto.getName());

		Genre savedGenre = genreRepository.save(g);

		GenreDto returnValue = new GenreDto();
		returnValue.setId(savedGenre.getId());
		returnValue.setName(savedGenre.getName());

		return returnValue;
	}

	private GenreDto dataTransfer(Genre genre) {

		GenreDto dto = new GenreDto();

		dto.setId(genre.getId());
		dto.setName(genre.getName());

		List<Movie> movies = genre.getMovies();
		List<MovieDto> movieDtos = new ArrayList<>();
		for (Movie m : movies) {
			MovieDto mdto = new MovieDto();
			mdto.setId(m.getId());
			mdto.setName(m.getName());
			mdto.setDirectors(m.getDirectors());
			mdto.setCast(m.getCast());
			mdto.setPoster(m.getPoster());
			mdto.setReleaseDate(m.getReleaseDate());
			List<Language> languages = m.getLanguages();
			List<LanguageDto> languageDtos = new ArrayList<>();
			for (Language l : languages) {
				LanguageDto ldto = new LanguageDto();
				ldto.setId(l.getId());
				ldto.setName(l.getName());
				languageDtos.add(ldto);
			}
			mdto.setLanguages(languageDtos);
			movieDtos.add(mdto);	
		}
		dto.setMovies(movieDtos);

		return dto;

	}

	@Override
	public GenreDto getGenreById(int id) throws NoSuchGenreException {

		Genre genre = genreRepository.findById(id).orElseThrow(
				() -> new NoSuchGenreException(env.getProperty(ExceptionConstants.GENRE_NOT_FOUND.toString())));

		GenreDto dto = dataTransfer(genre);

		return dto;
	}

	@Override
	public List<GenreDto> getAllGenres() {

		List<Genre> genres = (List<Genre>) genreRepository.findAll();
		List<GenreDto> dtos = new ArrayList<>();
		for (Genre g : genres) {
			GenreDto gdto = dataTransfer(g);
			dtos.add(gdto);
		}
		return dtos;
	}

	@Override
	public GenreDto updateGenre(GenreDto dto) throws NoSuchGenreException {
		Genre genre = genreRepository.findById(dto.getId()).orElseThrow(
				() -> new NoSuchGenreException(env.getProperty(ExceptionConstants.GENRE_NOT_FOUND.toString())));

		if (genre.getName() != null && !genre.getName().equals(dto.getName())) {
			genre.setName(dto.getName());
		}

		Genre saved = genreRepository.save(genre);

		GenreDto gdto = dataTransfer(saved);
		return gdto;
	}

	@Override
	public OutputMessage deleteGenre(int id) throws NoSuchGenreException {

		Genre genre = genreRepository.findById(id).orElseThrow(
				() -> new NoSuchGenreException(env.getProperty(ExceptionConstants.GENRE_NOT_FOUND.toString())));

		genreRepository.delete(genre);

		OutputMessage om = new OutputMessage();
		om.setMessage(env.getProperty(CrudMessage.GENRE_DELETE_SUCCESS.toString()));

		return om;
	}

}
