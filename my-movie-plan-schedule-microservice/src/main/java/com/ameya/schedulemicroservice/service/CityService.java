package com.ameya.schedulemicroservice.service;

import java.util.List;

import com.ameya.schedulemicroservice.dto.CityDto;
import com.ameya.schedulemicroservice.exception.city.CityAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.city.NoSuchCityException;
import com.ameya.schedulemicroservice.utils.OutputMessage;

public interface CityService {
	
	CityDto addCity(CityDto city) throws CityAlreadyExistsException;
	
	CityDto getCityById(int id) throws NoSuchCityException;
	
	List<CityDto> getAllCities();

	CityDto updateCity(CityDto cityDto) throws NoSuchCityException;

	OutputMessage deleteCity(int id) throws NoSuchCityException;

}
