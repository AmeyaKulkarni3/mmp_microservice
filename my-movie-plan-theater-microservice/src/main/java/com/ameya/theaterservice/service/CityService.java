package com.ameya.theaterservice.service;

import java.util.List;

import com.ameya.theaterservice.dto.CityDto;
import com.ameya.theaterservice.exception.city.CityAlreadyExistsException;
import com.ameya.theaterservice.exception.city.NoSuchCityException;
import com.ameya.theaterservice.utils.OutputMessage;

public interface CityService {
	
	CityDto addCity(CityDto city) throws CityAlreadyExistsException;
	
	CityDto getCityById(int id) throws NoSuchCityException;
	
	List<CityDto> getAllCities();

	CityDto updateCity(CityDto cityDto) throws NoSuchCityException;

	OutputMessage deleteCity(int id) throws NoSuchCityException;

}
