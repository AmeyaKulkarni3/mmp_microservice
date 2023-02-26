package com.ameya.moviemicroservice.service;

import java.util.List;

import com.ameya.moviemicroservice.dto.LanguageDto;
import com.ameya.moviemicroservice.exception.language.LanguageAlreadyExistsException;
import com.ameya.moviemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.moviemicroservice.utils.OutputMessage;

public interface LanguageService {
	
	LanguageDto addLanguage(LanguageDto dto) throws LanguageAlreadyExistsException;
	
	LanguageDto getLanguageById(int id) throws NoSuchLanguageException;
	
	List<LanguageDto> getAllLanguages();
	
	LanguageDto updateLanguage(LanguageDto dto) throws NoSuchLanguageException;
	
	OutputMessage deleteLanguage(int id) throws NoSuchLanguageException;

}
