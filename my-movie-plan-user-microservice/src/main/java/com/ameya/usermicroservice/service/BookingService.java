package com.ameya.usermicroservice.service;

import java.util.List;

import com.ameya.usermicroservice.dto.BookingDto;
import com.ameya.usermicroservice.dto.ScheduleDto;
import com.ameya.usermicroservicemodel.request.CreateBookingRequestModel;

public interface BookingService {
	
	BookingDto createBooking(CreateBookingRequestModel dto, ScheduleDto schedule);
	
	BookingDto getBookingById(int id);
	
	List<BookingDto> getAllUserBookings(String userId);
	
	String deleteBooking(int id);

}
