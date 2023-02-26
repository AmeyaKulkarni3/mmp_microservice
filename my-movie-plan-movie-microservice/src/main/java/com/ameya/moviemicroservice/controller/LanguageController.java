package com.ameya.moviemicroservice.controller;

import java.util.List;

import com.ameya.moviemicroservice.dto.GenreDto;
import com.ameya.moviemicroservice.dto.LanguageDto;
import com.ameya.moviemicroservice.exception.language.LanguageAlreadyExistsException;
import com.ameya.moviemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.moviemicroservice.service.LanguageService;
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
@RequestMapping("/language")
public class LanguageController {

	@Autowired
	LanguageService languageService;
	
	@Autowired
	Environment env;

//	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<LanguageDto> addLanguage(@RequestBody LanguageDto languageDto) throws LanguageAlreadyExistsException {
		
		RestTemplate rt = new RestTemplate();
		
		rt.postForEntity(env.getProperty("add.language.url"), languageDto, LanguageDto.class);

		return ResponseEntity.ok(languageService.addLanguage(languageDto));

	}

	@GetMapping("/{id}")
	public ResponseEntity<LanguageDto> getLanguageById(@PathVariable int id) throws NoSuchLanguageException{

		return ResponseEntity.ok(languageService.getLanguageById(id));
	}

	@GetMapping
	public ResponseEntity<List<LanguageDto>> getAllLanguages() {

		return ResponseEntity.ok(languageService.getAllLanguages());
	}

//	@Secured("ROLE_ADMIN")
	@PutMapping
	public ResponseEntity<LanguageDto> updateLanguage(@RequestBody LanguageDto languageDto) throws NoSuchLanguageException{
		return ResponseEntity.ok(languageService.updateLanguage(languageDto));
	}

//	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<OutputMessage> deleteLanguage(@PathVariable int id) throws NoSuchLanguageException{
		return ResponseEntity.ok(languageService.deleteLanguage(id));
	}

}
