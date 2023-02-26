package com.ameya.schedulemicroservice.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ameya.schedulemicroservice.dto.AddressDto;
import com.ameya.schedulemicroservice.dto.CityDto;
import com.ameya.schedulemicroservice.dto.PartnerDto;
import com.ameya.schedulemicroservice.dto.ScheduleDto;
import com.ameya.schedulemicroservice.dto.TheaterDto;
import com.ameya.schedulemicroservice.dto.TierDto;
import com.ameya.schedulemicroservice.entity.Address;
import com.ameya.schedulemicroservice.entity.City;
import com.ameya.schedulemicroservice.entity.Partner;
import com.ameya.schedulemicroservice.entity.Schedule;
import com.ameya.schedulemicroservice.entity.Theater;
import com.ameya.schedulemicroservice.entity.Tier;
import com.ameya.schedulemicroservice.exception.ExceptionConstants;
import com.ameya.schedulemicroservice.exception.city.NoSuchCityException;
import com.ameya.schedulemicroservice.exception.partner.NoSuchPartnerException;
import com.ameya.schedulemicroservice.exception.showtime.ShowtimeAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.theater.NoSuchTheaterException;
import com.ameya.schedulemicroservice.exception.theater.TheaterAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.tier.NoSuchTierException;
import com.ameya.schedulemicroservice.exception.tier.TierAlreadyExistsException;
import com.ameya.schedulemicroservice.repository.AddressRepository;
import com.ameya.schedulemicroservice.repository.CityRepository;
import com.ameya.schedulemicroservice.repository.PartnerRepository;
import com.ameya.schedulemicroservice.repository.ShowtimeRepository;
import com.ameya.schedulemicroservice.repository.TheaterRepository;
import com.ameya.schedulemicroservice.repository.TierRepository;
import com.ameya.schedulemicroservice.service.ShowtimeService;
import com.ameya.schedulemicroservice.service.TheaterService;
import com.ameya.schedulemicroservice.service.TierService;
import com.ameya.schedulemicroservice.utils.CrudMessage;
import com.ameya.schedulemicroservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:TheaterValidationMessages.properties")
@Transactional
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	TheaterRepository theaterRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	TierService tierService;

	@Autowired
	ShowtimeRepository showtimeRepository;

	@Autowired
	ShowtimeService showtimeService;

	@Autowired
	TierRepository tierRepository;
	
	@Autowired
	PartnerRepository partnerRepository;

	@Autowired
	Environment env;

	@Override
	public TheaterDto addTheater(TheaterDto theater) throws TheaterAlreadyExistsException, TierAlreadyExistsException,
			ShowtimeAlreadyExistsException, NoSuchTierException, NoSuchTheaterException, NoSuchCityException, NoSuchPartnerException {

		Partner savedPartner = partnerRepository.findByName(theater.getPartner().getName());
		
		if (savedPartner == null) {
			throw new NoSuchPartnerException(
					env.getProperty(ExceptionConstants.NO_SUCH_PARTNER.toString()));
		}
		
		Theater saved = theaterRepository.findByName(theater.getName());

		if (saved != null) {
			throw new TheaterAlreadyExistsException(
					env.getProperty(ExceptionConstants.THEATER_ALREADY_EXISTS.toString()));
		}

		Theater t = new Theater();
		t.setName(theater.getName());

		City c = cityRepository.findByName(theater.getCity().getName());
		
		if(c == null) {
			throw new NoSuchCityException(env.getProperty(ExceptionConstants.CITY_NOT_FOUND.toString()));
		}

		t.setCity(c);

		Address a = new Address();
		a.setLine1(theater.getAddress().getLine1());
		a.setLine2(theater.getAddress().getLine2());
		a.setPincode(theater.getAddress().getPincode());
		a.setCity(c);

		Address savedAddress = addressRepository.save(a);

		t.setAddress(savedAddress);

		List<TierDto> tiers = theater.getTiers();
		Collections.sort(tiers);
		List<Tier> tierEntities = new ArrayList<>();
		for (TierDto tdto : tiers) {
			if (tdto.getId() != 0) {
				Tier savedTier = tierRepository.findById(tdto.getId()).orElseThrow(
						() -> new NoSuchTierException(env.getProperty(ExceptionConstants.TIER_NOT_FOUND.toString())));
				tierEntities.add(savedTier);
			} else {
//				TierDto savedTierDto = tierService.addTier(tdto);
//				Tier tEntity = new Tier();
//				tEntity.setId(savedTierDto.getId());
//				tEntity.setName(savedTierDto.getName());
//				tEntity.setPrice(savedTierDto.getPrice());
//				tEntity.setPriority(savedTierDto.getPriority());
//				tEntity.setSeats(savedTierDto.getSeats());
//				tierEntities.add(tEntity);
				Tier tEntity = new Tier();
				tEntity.setName(tdto.getName());
				tEntity.setPrice(tdto.getPrice());
				tEntity.setPriority(tdto.getPriority());
				tEntity.setNoOfSeats(tdto.getNoOfSeats());
				tEntity.setRows(tdto.getRows());
				tEntity.setCols(tdto.getCols());
				tEntity.setSeatsBooked(0);

				tierEntities.add(tEntity);
			}

		}
		t.setPartner(savedPartner);
		t.setTiers(tierEntities);
		t.setSchedules(new ArrayList<Schedule>());

		Theater savedTheater = theaterRepository.save(t);
		
		List<Tier> savedTiers = savedTheater.getTiers();
		for(Tier ti : savedTiers) {
			List<Theater> theaters = ti.getTheaters();
			if(theaters == null) {
				theaters = new ArrayList<>();
			}
			theaters.add(savedTheater);
			ti.setTheaters(theaters);
			tierRepository.save(ti);
		}
		
		Address add = savedTheater.getAddress();
		add.setTheater(savedTheater);
		addressRepository.save(add);

		TheaterDto returnValue = dataTransfer(theaterRepository.findById(savedTheater.getId()).orElseThrow(() -> new NoSuchTheaterException(env.getProperty(ExceptionConstants.THEATER_NOT_FOUND.toString()))));

		return returnValue;
	}

	private TheaterDto dataTransfer(Theater t) {
		TheaterDto dto = new TheaterDto();
		dto.setId(t.getId());
		dto.setName(t.getName());
		Address a = t.getAddress();
		AddressDto adto = new AddressDto();
		adto.setId(a.getId());
		adto.setLine1(a.getLine1());
		adto.setLine2(a.getLine2());
		adto.setPincode(a.getPincode());
		CityDto cdto = new CityDto();
		City c = t.getCity();
		cdto.setId(c.getId());
		cdto.setName(c.getName());
		adto.setCityDto(cdto);
		dto.setAddress(adto);
		dto.setCity(cdto);
		List<Tier> tiers = t.getTiers();
		List<TierDto> tdtos = new ArrayList<>();
		for (Tier tier : tiers) {
			TierDto tdto = new TierDto();
			tdto.setId(tier.getId());
			tdto.setName(tier.getName());
			tdto.setPrice(tier.getPrice());
			tdto.setPriority(tier.getPriority());
			tdto.setNoOfSeats(tier.getNoOfSeats());
			tdto.setRows(tier.getRows());
			tdto.setCols(tier.getCols());
			tdtos.add(tdto);
		}
		dto.setTiers(tdtos);
		List<Schedule> schedules = t.getSchedules();
		List<ScheduleDto> sdtos = new ArrayList<>();
		PartnerDto pdto = new PartnerDto();
		Partner p = t.getPartner();
		pdto.setId(p.getId());
		pdto.setName(p.getName());
		dto.setSchedules(sdtos);
		dto.setPartner(pdto);
		return dto;
	}

	@Override
	public TheaterDto getTheaterById(int id) throws NoSuchTheaterException {
		Theater saved = theaterRepository.findById(id).orElseThrow(
				() -> new NoSuchTheaterException(env.getProperty(ExceptionConstants.THEATER_NOT_FOUND.toString())));
		TheaterDto dto = dataTransfer(saved);
		return dto;
	}

	@Override
	public List<TheaterDto> getAllTheaters() {
		List<Theater> theaters = (List<Theater>) theaterRepository.findAll();
		List<TheaterDto> tdtos = new ArrayList<>();
		for (Theater t : theaters) {
			TheaterDto tdto = dataTransfer(t);
			tdtos.add(tdto);
		}
		return tdtos;
	}

	@Override
	public TheaterDto updateTheater(TheaterDto theaterDto) throws NoSuchTheaterException {

		Theater theater = theaterRepository.findById(theaterDto.getId()).orElseThrow(
				() -> new NoSuchTheaterException(env.getProperty(ExceptionConstants.THEATER_NOT_FOUND.toString())));

		if (theaterDto.getName() != null && !theater.getName().equals(theaterDto.getName())) {
			theater.setName(theaterDto.getName());
		}

		Theater saved = theaterRepository.save(theater);

		return dataTransfer(saved);
	}

	@Override
	public OutputMessage deleteTheater(int id) throws NoSuchTheaterException {
		Theater theater = theaterRepository.findById(id).orElseThrow(
				() -> new NoSuchTheaterException(env.getProperty(ExceptionConstants.THEATER_NOT_FOUND.toString())));
		theaterRepository.delete(theater);
		OutputMessage message = new OutputMessage();
		message.setMessage(env.getProperty(CrudMessage.THEATER_DELETE_SUCCESS.toString()));
		return message;
	}

}
