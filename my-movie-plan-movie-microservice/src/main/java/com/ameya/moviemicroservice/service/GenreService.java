package com.ameya.moviemicroservice.service;

import java.util.List;

import com.ameya.moviemicroservice.dto.GenreDto;
import com.ameya.moviemicroservice.exception.genre.GenreAlreadyExistsException;
import com.ameya.moviemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.moviemicroservice.utils.OutputMessage;

public interface GenreService {
	
	GenreDto addGenre(GenreDto dto) throws GenreAlreadyExistsException;
	
	GenreDto getGenreById(int id) throws NoSuchGenreException;
	
	List<GenreDto> getAllGenres();
	
	GenreDto updateGenre(GenreDto dto) throws NoSuchGenreException;
	
	OutputMessage deleteGenre(int id) throws NoSuchGenreException;

}
