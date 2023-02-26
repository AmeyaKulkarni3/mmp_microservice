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
import com.ameya.moviemicroservice.exception.language.LanguageAlreadyExistsException;
import com.ameya.moviemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.moviemicroservice.repository.LanguageRepository;
import com.ameya.moviemicroservice.service.LanguageService;
import com.ameya.moviemicroservice.utils.CrudMessage;
import com.ameya.moviemicroservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:LanguageValidationMessages.properties")
@Transactional
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	LanguageRepository languageRepository;

	@Autowired
	Environment env;

	@Override
	public LanguageDto addLanguage(LanguageDto dto) throws LanguageAlreadyExistsException {

		Language language = languageRepository.findByName(dto.getName());
		if (language != null) {
			throw new LanguageAlreadyExistsException(
					env.getProperty(ExceptionConstants.LANGUAGE_ALREADY_EXISTS.toString()));
		}

		Language l = new Language();
		l.setName(dto.getName());

		Language saved = languageRepository.save(l);

		LanguageDto ldto = new LanguageDto();

		ldto.setId(saved.getId());
		ldto.setName(saved.getName());

		return ldto;
	}

	private LanguageDto dataTransfer(Language language) {
		LanguageDto dto = new LanguageDto();
		dto.setId(language.getId());
		dto.setName(language.getName());
		List<Movie> movies = language.getMovies();
		List<MovieDto> movieDtos = new ArrayList<>();
		for (Movie m : movies) {
			MovieDto mdto = new MovieDto();
			mdto.setId(m.getId());
			mdto.setName(m.getName());
			mdto.setDirectors(m.getDirectors());
			mdto.setCast(m.getCast());
			mdto.setPoster(m.getPoster());
			mdto.setReleaseDate(m.getReleaseDate());
			List<Genre> genres = m.getGenres();
			List<GenreDto> genreDtos = new ArrayList<>();
			for (Genre g : genres) {
				GenreDto gdto = new GenreDto();
				gdto.setId(g.getId());
				gdto.setName(g.getName());
				genreDtos.add(gdto);
			}
			mdto.setGenres(genreDtos);
			movieDtos.add(mdto);
		}
		dto.setMovies(movieDtos);
		return dto;

	}

	@Override
	public LanguageDto getLanguageById(int id) throws NoSuchLanguageException {
		Language language = languageRepository.findById(id).orElseThrow(
				() -> new NoSuchLanguageException(env.getProperty(ExceptionConstants.LANGUAGE_NOT_FOUND.toString())));
		LanguageDto dto = dataTransfer(language);
		return dto;
	}

	@Override
	public List<LanguageDto> getAllLanguages() {
		List<Language> languages = (List<Language>) languageRepository.findAll();
		List<LanguageDto> dtos = new ArrayList<>();
		for (Language l : languages) {
			LanguageDto ldto = dataTransfer(l);
			dtos.add(ldto);
		}
		return dtos;
	}

	@Override
	public LanguageDto updateLanguage(LanguageDto dto) throws NoSuchLanguageException {
		Language language = languageRepository.findById(dto.getId()).orElseThrow(
				() -> new NoSuchLanguageException(env.getProperty(ExceptionConstants.LANGUAGE_NOT_FOUND.toString())));
		if(dto.getName() != null && !dto.getName().equals(language.getName())) {
			language.setName(dto.getName());
		}
		
		Language updated = languageRepository.save(language);
		
		LanguageDto returnValue = dataTransfer(updated);
		
		return returnValue;
	}

	@Override
	public OutputMessage deleteLanguage(int id) throws NoSuchLanguageException {
		Language language = languageRepository.findById(id).orElseThrow(
				() -> new NoSuchLanguageException(env.getProperty(ExceptionConstants.LANGUAGE_NOT_FOUND.toString())));
		languageRepository.delete(language);
		
		OutputMessage om = new OutputMessage();
		om.setMessage(env.getProperty(CrudMessage.LANGUAGE_DELETE_SUCCESS.toString()));
		
		return om;
	}

}
