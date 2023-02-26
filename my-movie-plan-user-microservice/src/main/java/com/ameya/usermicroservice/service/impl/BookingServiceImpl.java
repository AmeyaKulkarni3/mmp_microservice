package com.ameya.usermicroservice.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ameya.usermicroservice.dto.AddressDto;
import com.ameya.usermicroservice.dto.BookingDto;
import com.ameya.usermicroservice.dto.CityDto;
import com.ameya.usermicroservice.dto.GenreDto;
import com.ameya.usermicroservice.dto.LanguageDto;
import com.ameya.usermicroservice.dto.MovieDto;
import com.ameya.usermicroservice.dto.ScheduleDto;
import com.ameya.usermicroservice.dto.SeatDto;
import com.ameya.usermicroservice.dto.ShowtimeDto;
import com.ameya.usermicroservice.dto.TheaterDto;
import com.ameya.usermicroservice.dto.TierDto;
import com.ameya.usermicroservice.dto.UserDto;
import com.ameya.usermicroservice.entity.Booking;
import com.ameya.usermicroservice.entity.UserEntity;
import com.ameya.usermicroservice.exception.ExceptionConstants;
import com.ameya.usermicroservice.repository.BookingRepository;
import com.ameya.usermicroservice.repository.UserRepository;
import com.ameya.usermicroservice.service.BookingService;
import com.ameya.usermicroservicemodel.request.CreateBookingRequestModel;
import com.ameya.usermicroservicemodel.request.UpdateSeatStatusModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:BookingValidationMessages.properties")
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Environment env;

	@Override
	public BookingDto createBooking(CreateBookingRequestModel dto, ScheduleDto schedule){

		Booking booking = new Booking();
		List<Integer> seats = dto.getSeatNumbers();
		List<SeatDto> seatDtos = schedule.getSeats();
		double totalPrice = 0;
		for (Integer num : seats) {
			SeatDto sdto = seatDtos.stream()
					.filter(seat -> seat.getId() == num)
					.findFirst()
					.orElse(null);
			TierDto tier = sdto.getTier();
			totalPrice = totalPrice + tier.getPrice();
		}
		
		UserEntity user = userRepository.findByUserId(dto.getUserId());
		
		booking.setScheduleId(schedule.getId());
		List<SeatDto> scheduleSeats = schedule.getSeats();
		String selected = "";
		for(SeatDto sdto : scheduleSeats) {
			for(Integer n : dto.getSeatNumbers()) {
				if(sdto.getId() == n) {
					selected = selected + sdto.getSeatNumber() + ",";
				}
			}
		}
		selected = selected.substring(0,selected.length()-1);
		booking.setSeats(selected);
		booking.setTotalPrice(totalPrice);
		booking.setUser(user);
		
		Booking saved = bookingRepository.save(booking);

		List<Booking> userBookings = user.getBookings();
		userBookings.add(saved);
		user.setBookings(userBookings);
		userRepository.save(user);
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setPhone(user.getPhone());
		userDto.setUserId(user.getUserId());
		
		BookingDto bookingDto = new BookingDto();
		bookingDto.setId(saved.getId());
		bookingDto.setSchedule(schedule);
		bookingDto.setSeats(saved.getSeats());
		bookingDto.setUser(userDto);
		
		return bookingDto;

	}
	
	private BookingDto dataTransfer(Booking b) {
//		BookingDto dto = new BookingDto();
//		dto.setId(b.getId());
//		ScheduleDto sdto = new ScheduleDto();
//		
//		Schedule s = b.getSchedule();
//		Movie m = s.getMovie();
//		Theater t = s.getTheater();
//		Showtime st = s.getShowtime();
//		
//		MovieDto mdto = new MovieDto();
//		mdto.setId(m.getId());
//		mdto.setName(m.getName());
//		mdto.setDirectors(m.getDirectors());
//		mdto.setCast(m.getCast());
//		mdto.setDuration(m.getDuration());
//		mdto.setReleaseDate(m.getReleaseDate());
//		List<Genre> gs = m.getGenres();
//		List<GenreDto> gdtos = new ArrayList<>();
//		for(Genre g : gs) {
//			GenreDto gdto = new GenreDto();
//			gdto.setId(g.getId());
//			gdto.setName(g.getName());
//			gdtos.add(gdto);
//		}
//		mdto.setGenres(gdtos);
//		List<Language> ls = m.getLanguages();
//		List<LanguageDto> ldtos = new ArrayList<>();
//		for(Language l : ls) {
//			LanguageDto ldto = new LanguageDto();
//			ldto.setId(l.getId());
//			ldto.setName(l.getName());
//			ldtos.add(ldto);
//		}
//		mdto.setLanguages(ldtos);
//		
//		TheaterDto tdto = new TheaterDto();
//		tdto.setId(t.getId());
//		tdto.setName(t.getName());
//		City c = t.getCity();
//		CityDto cdto = new CityDto();
//		cdto.setId(c.getId());
//		cdto.setName(c.getName());
//		tdto.setCity(cdto);
//		Address a = t.getAddress();
//		AddressDto adto = new AddressDto();
//		adto.setId(a.getId());
//		adto.setLine1(a.getLine1());
//		adto.setLine2(a.getLine2());
//		adto.setPincode(a.getPincode());
//		adto.setCityDto(cdto);
//		tdto.setAddress(adto);
//		
//		ShowtimeDto stdto = new ShowtimeDto();
//		stdto.setId(st.getId());
//		stdto.setTime(st.getTime());
//		
//		sdto.setId(s.getId());
//		sdto.setDate(s.getDate());
//		sdto.setMovie(mdto);
//		sdto.setTheater(tdto);
//		sdto.setShowtime(stdto);
//		dto.setSchedule(sdto);
//		
//		List<Seat> seats = b.getSeats();
//		List<SeatDto> seatDtos = new ArrayList<>(); 
//		for(Seat seat : seats) {
//			SeatDto seatDto = new SeatDto();
//			seatDto.setId(seat.getId());
//			seatDto.setSeatNumber(seat.getSeatNumber());
//			seatDto.setBooked(seat.isBooked());
//			Tier tier = seat.getTier();
//			TierDto tierDto = new TierDto();
//			tierDto.setId(tier.getId());
//			tierDto.setName(tier.getName());
//			tierDto.setNoOfSeats(tier.getNoOfSeats());
//			tierDto.setSeatsBooked(tier.getSeatsBooked());
//			tierDto.setPrice(tier.getPrice());
//			tierDto.setPriority(tier.getPriority());
//			seatDto.setTier(tierDto);
//			seatDtos.add(seatDto);
//		}
//		
//		dto.setSeats(seatDtos);
//		dto.setTotalPrice(b.getTotalPrice());
//		
//		UserEntity user = b.getUser();
//		UserDto udto = new UserDto();
//		udto.setId(user.getId());
//		udto.setFirstName(user.getFirstName());
//		udto.setLastName(user.getLastName());
//		udto.setEmail(user.getEmail());
//		udto.setPhone(user.getPhone());
//		udto.setUserId(user.getUserId());
//		dto.setUser(udto);
		
//		return dto;
		
		return null;
		
	}

	@Override
	public BookingDto getBookingById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookingDto> getAllUserBookings(String userId) {
		UserEntity user = userRepository.findByUserId(userId);
		List<Booking> bookings = user.getBookings();
		List<BookingDto> bdtos = new ArrayList<>();
		for(Booking b : bookings) {
			BookingDto bdto = dataTransfer(b);
			bdtos.add(bdto);
		}
		Collections.sort(bdtos);
		return bdtos;
	}

	@Override
	public String deleteBooking(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
