package com.ameya.schedulemicroservice.service;

import java.util.List;

import com.ameya.schedulemicroservice.dto.LanguageDto;
import com.ameya.schedulemicroservice.exception.language.LanguageAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.schedulemicroservice.utils.OutputMessage;

public interface LanguageService {
	
	LanguageDto addLanguage(LanguageDto dto) throws LanguageAlreadyExistsException;
	
	LanguageDto getLanguageById(int id) throws NoSuchLanguageException;
	
	List<LanguageDto> getAllLanguages();
	
	LanguageDto updateLanguage(LanguageDto dto) throws NoSuchLanguageException;
	
	OutputMessage deleteLanguage(int id) throws NoSuchLanguageException;

}
