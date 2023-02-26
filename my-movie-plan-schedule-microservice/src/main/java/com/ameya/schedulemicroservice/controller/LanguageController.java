package com.ameya.schedulemicroservice.controller;

import java.util.List;

import com.ameya.schedulemicroservice.dto.LanguageDto;
import com.ameya.schedulemicroservice.exception.language.LanguageAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.schedulemicroservice.service.LanguageService;
import com.ameya.schedulemicroservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
public class LanguageController {

	@Autowired
	LanguageService languageService;

//	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<LanguageDto> addGenre(@RequestBody LanguageDto languageDto) throws LanguageAlreadyExistsException {

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
