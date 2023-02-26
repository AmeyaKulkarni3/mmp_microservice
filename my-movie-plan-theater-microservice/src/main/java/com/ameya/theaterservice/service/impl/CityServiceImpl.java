package com.ameya.theaterservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ameya.theaterservice.dto.AddressDto;
import com.ameya.theaterservice.dto.CityDto;
import com.ameya.theaterservice.dto.GenreDto;
import com.ameya.theaterservice.dto.LanguageDto;
import com.ameya.theaterservice.dto.MovieDto;
import com.ameya.theaterservice.dto.ScheduleDto;
import com.ameya.theaterservice.dto.ShowtimeDto;
import com.ameya.theaterservice.dto.TheaterDto;
import com.ameya.theaterservice.entity.Address;
import com.ameya.theaterservice.entity.City;
import com.ameya.theaterservice.entity.Schedule;
import com.ameya.theaterservice.entity.Showtime;
import com.ameya.theaterservice.entity.Theater;
import com.ameya.theaterservice.exception.ExceptionConstants;
import com.ameya.theaterservice.exception.city.CityAlreadyExistsException;
import com.ameya.theaterservice.exception.city.NoSuchCityException;
import com.ameya.theaterservice.repository.AddressRepository;
import com.ameya.theaterservice.repository.CityRepository;
import com.ameya.theaterservice.repository.TheaterRepository;
import com.ameya.theaterservice.service.CityService;
import com.ameya.theaterservice.utils.CrudMessage;
import com.ameya.theaterservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:CityValidationMessages.properties")
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	TheaterRepository theaterRepository;

	@Autowired
	Environment env;

	@Override
	public CityDto addCity(CityDto city) throws CityAlreadyExistsException {

		City cityEntity = new City();

		City c = cityRepository.findByName(city.getName());

		if (c != null) {
			throw new CityAlreadyExistsException(env.getProperty(ExceptionConstants.CITY_ALREADY_EXISTS.toString()));
		}

		cityEntity.setName(city.getName());

		City savedCity = cityRepository.save(cityEntity);

		CityDto returnValue = new CityDto();
		returnValue.setId(savedCity.getId());
		returnValue.setName(savedCity.getName());

		return returnValue;
	}

	@Override
	public CityDto getCityById(int id) throws NoSuchCityException {

		City c = cityRepository.findById(id).orElseThrow(
				() -> new NoSuchCityException(env.getProperty(ExceptionConstants.CITY_NOT_FOUND.toString())));

		CityDto dto = dataTransfer(c);

		return dto;
	}

	@Override
	public List<CityDto> getAllCities() {

		List<City> cities = (List<City>) cityRepository.findAll();
		
		List<CityDto> cityDtos = new ArrayList<>();
		for(City c : cities) {
			CityDto dto = dataTransfer(c);
			cityDtos.add(dto);
		}
		return cityDtos;
	}
	
	@Override
	public CityDto updateCity(CityDto cityDto) throws NoSuchCityException {
		
		City c = cityRepository.findById(cityDto.getId()).orElseThrow(
				() -> new NoSuchCityException(env.getProperty(ExceptionConstants.CITY_NOT_FOUND.toString())));
		
		if(cityDto.getName() != null && !cityDto.getName().equals(c.getName())) {
			c.setName(cityDto.getName());
		}
		
		cityRepository.save(c);
		
		CityDto dto = dataTransfer(c);
		
		return dto;
	}
	
	@Override
	public OutputMessage deleteCity(int id) throws NoSuchCityException {
		
		City c = cityRepository.findById(id).orElseThrow(
				() -> new NoSuchCityException(env.getProperty(ExceptionConstants.CITY_NOT_FOUND.toString())));
		
		List<Theater> theaters = c.getTheatres();
		List<Address> addresses = c.getAddresses();
		
		for(Address a : addresses) {
			addressRepository.delete(a);
		}
		
		for(Theater t : theaters) {
			theaterRepository.delete(t);
		}
		
		cityRepository.delete(c);
		
		OutputMessage message = new OutputMessage();
		message.setMessage(env.getProperty(CrudMessage.CITY_DELETE_SUCCESS.toString()));
		
		return message;
	}

	private CityDto dataTransfer(City c) {

		CityDto dto = new CityDto();

		dto.setId(c.getId());
		dto.setName(c.getName());

		List<AddressDto> addresses = new ArrayList<>();
		for (Address ad : c.getAddresses()) {
			AddressDto adto = new AddressDto();
			adto.setId(ad.getId());
			adto.setLine1(ad.getLine1());
			adto.setLine2(ad.getLine2());
			adto.setPincode(ad.getPincode());
			addresses.add(adto);
		}
		dto.setAddresses(addresses);

		List<TheaterDto> theaters = new ArrayList<>();
		for (Theater t : c.getTheatres()) {
			TheaterDto tdto = new TheaterDto();
			tdto.setId(t.getId());
			tdto.setName(t.getName());
			List<Schedule> schedules = t.getSchedules();
			List<ScheduleDto> stdtos = new ArrayList<>();
			tdto.setSchedules(stdtos);
			theaters.add(tdto);
		}
		dto.setTheatres(theaters);

		return dto;

	}

	

	

}
