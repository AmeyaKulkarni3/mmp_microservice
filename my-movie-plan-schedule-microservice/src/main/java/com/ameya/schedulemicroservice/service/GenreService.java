package com.ameya.schedulemicroservice.service;

import java.util.List;

import com.ameya.schedulemicroservice.dto.GenreDto;
import com.ameya.schedulemicroservice.exception.genre.GenreAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.schedulemicroservice.utils.OutputMessage;

public interface GenreService {
	
	GenreDto addGenre(GenreDto dto) throws GenreAlreadyExistsException;
	
	GenreDto getGenreById(int id) throws NoSuchGenreException;
	
	List<GenreDto> getAllGenres();
	
	GenreDto updateGenre(GenreDto dto) throws NoSuchGenreException;
	
	OutputMessage deleteGenre(int id) throws NoSuchGenreException;

}
