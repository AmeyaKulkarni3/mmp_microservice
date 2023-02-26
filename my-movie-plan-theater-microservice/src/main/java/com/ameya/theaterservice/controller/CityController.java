package com.ameya.theaterservice.controller;

import java.util.List;

import com.ameya.theaterservice.dto.CityDto;
import com.ameya.theaterservice.exception.city.CityAlreadyExistsException;
import com.ameya.theaterservice.exception.city.NoSuchCityException;
import com.ameya.theaterservice.service.CityService;
import com.ameya.theaterservice.utils.OutputMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
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
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	CityService cityService;
	
	@Autowired
	Environment env;
	
//	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<CityDto> addCity(@RequestBody CityDto cityDto) throws CityAlreadyExistsException{
		
		RestTemplate rt = new RestTemplate();
		
		rt.postForEntity(env.getProperty("add.city.url"), cityDto, CityDto.class);
		
		return ResponseEntity.ok(cityService.addCity(cityDto));
		
	}
	
//	@GetMapping("/{id}")
	public ResponseEntity<CityDto> getCityByName(@PathVariable int id) throws NoSuchCityException{
		
		return ResponseEntity.ok(cityService.getCityById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CityDto>> getAllCities(){
		
		return ResponseEntity.ok(cityService.getAllCities());
	}
	
//	@Secured("ROLE_ADMIN")
	@PutMapping
	public ResponseEntity<CityDto> updateCity(@RequestBody CityDto cityDto) throws NoSuchCityException{
		return ResponseEntity.ok(cityService.updateCity(cityDto));
	}
	
//	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<OutputMessage> deleteCity(@PathVariable int id) throws NoSuchCityException{
		return ResponseEntity.ok(cityService.deleteCity(id));
	}
	
	

}
