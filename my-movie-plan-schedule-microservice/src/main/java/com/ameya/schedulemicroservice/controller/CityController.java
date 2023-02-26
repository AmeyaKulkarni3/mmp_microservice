package com.ameya.schedulemicroservice.controller;

import java.util.List;

import com.ameya.schedulemicroservice.dto.CityDto;
import com.ameya.schedulemicroservice.exception.city.CityAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.city.NoSuchCityException;
import com.ameya.schedulemicroservice.service.CityService;
import com.ameya.schedulemicroservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	CityService cityService;
	
	@Autowired
	Environment env;
	
//	@Secured("ROLE_ADMIN")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<CityDto> addCity(@RequestBody CityDto cityDto) throws CityAlreadyExistsException{
		
		return ResponseEntity.ok(cityService.addCity(cityDto));
		
	}
	
	@GetMapping(path="/{id}",consumes = "application/json", produces = "application/json")
	public ResponseEntity<CityDto> getCityByName(@PathVariable int id) throws NoSuchCityException{
		
		return ResponseEntity.ok(cityService.getCityById(id));
	}
	
	@GetMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<CityDto>> getAllCities(){
		
		return ResponseEntity.ok(cityService.getAllCities());
	}
	
//	@Secured("ROLE_ADMIN")
	@PutMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<CityDto> updateCity(@RequestBody CityDto cityDto) throws NoSuchCityException{
		return ResponseEntity.ok(cityService.updateCity(cityDto));
	}
	
//	@Secured("ROLE_ADMIN")
	@DeleteMapping(path="/{id}",consumes = "application/json", produces = "application/json")
	public ResponseEntity<OutputMessage> deleteCity(@PathVariable int id) throws NoSuchCityException{
		return ResponseEntity.ok(cityService.deleteCity(id));
	}
	
	

}
