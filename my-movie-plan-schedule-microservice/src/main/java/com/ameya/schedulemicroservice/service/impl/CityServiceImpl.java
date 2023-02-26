package com.ameya.schedulemicroservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ameya.schedulemicroservice.dto.AddressDto;
import com.ameya.schedulemicroservice.dto.CityDto;
import com.ameya.schedulemicroservice.dto.GenreDto;
import com.ameya.schedulemicroservice.dto.LanguageDto;
import com.ameya.schedulemicroservice.dto.MovieDto;
import com.ameya.schedulemicroservice.dto.ScheduleDto;
import com.ameya.schedulemicroservice.dto.ShowtimeDto;
import com.ameya.schedulemicroservice.dto.TheaterDto;
import com.ameya.schedulemicroservice.entity.Address;
import com.ameya.schedulemicroservice.entity.City;
import com.ameya.schedulemicroservice.entity.Genre;
import com.ameya.schedulemicroservice.entity.Language;
import com.ameya.schedulemicroservice.entity.Movie;
import com.ameya.schedulemicroservice.entity.Schedule;
import com.ameya.schedulemicroservice.entity.Showtime;
import com.ameya.schedulemicroservice.entity.Theater;
import com.ameya.schedulemicroservice.exception.ExceptionConstants;
import com.ameya.schedulemicroservice.exception.city.CityAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.city.NoSuchCityException;
import com.ameya.schedulemicroservice.repository.AddressRepository;
import com.ameya.schedulemicroservice.repository.CityRepository;
import com.ameya.schedulemicroservice.repository.TheaterRepository;
import com.ameya.schedulemicroservice.service.CityService;
import com.ameya.schedulemicroservice.utils.CrudMessage;
import com.ameya.schedulemicroservice.utils.OutputMessage;

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
			for(Schedule s : schedules) {
				ScheduleDto sdto = new ScheduleDto();
				Movie m = s.getMovie();
				Showtime st = s.getShowtime();
				MovieDto mdto = new MovieDto();
				mdto.setId(m.getId());
				mdto.setName(m.getName());
				mdto.setDirectors(m.getDirectors());
				mdto.setCast(m.getCast());
				mdto.setPoster(m.getPoster());
				mdto.setReleaseDate(m.getReleaseDate());
				List<GenreDto> genres = new ArrayList<>();
				for (Genre g : m.getGenres()) {
					GenreDto gdto = new GenreDto();
					gdto.setId(g.getId());
					gdto.setName(g.getName());
					genres.add(gdto);
				}
				mdto.setGenres(genres);
				List<LanguageDto> languages = new ArrayList<>();
				for (Language l : m.getLanguages()) {
					LanguageDto ldto = new LanguageDto();
					ldto.setId(l.getId());
					ldto.setName(l.getName());
					languages.add(ldto);
				}
				mdto.setLanguages(languages);
				sdto.setMovie(mdto);
				ShowtimeDto stdto = new ShowtimeDto();
				stdto.setId(st.getId());
				stdto.setTime(st.getTime());
				sdto.setShowtime(stdto);
				sdto.setTheater(null);
				stdtos.add(sdto);
			}
			tdto.setSchedules(stdtos);
			theaters.add(tdto);
		}
		dto.setTheatres(theaters);

		return dto;

	}

	

	

}
