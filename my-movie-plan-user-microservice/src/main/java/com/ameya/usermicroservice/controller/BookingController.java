package com.ameya.usermicroservice.controller;

import java.util.List;

import com.ameya.usermicroservice.dto.BookingDto;
import com.ameya.usermicroservice.dto.ScheduleDto;
import com.ameya.usermicroservice.service.BookingService;
import com.ameya.usermicroservicemodel.request.CreateBookingRequestModel;
import com.ameya.usermicroservicemodel.request.UpdateSeatStatusModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	Environment env;

	@PostMapping
	public ResponseEntity<BookingDto> createBooking(@RequestBody CreateBookingRequestModel createBooking) {

		RestTemplate rt = new RestTemplate();
		ScheduleDto schedule = rt.getForObject(
				env.getProperty("get.schedule.url") + "/" + createBooking.getScheduleId(), ScheduleDto.class);
		List<Integer> seats = createBooking.getSeatNumbers();
		UpdateSeatStatusModel seatStatus = new UpdateSeatStatusModel();
		seatStatus.setScheduleId(createBooking.getScheduleId());
		seatStatus.setSeatNumbers(seats);
		rt.put(env.getProperty("update.seat.status.url"),seatStatus);
//		rt.postForEntity(env.getProperty("update.seat.status.url"), seatStatus, String.class);
		return ResponseEntity.ok(bookingService.createBooking(createBooking, schedule));

	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<BookingDto>> getAllBookings(@PathVariable String userId) {
		return ResponseEntity.ok(bookingService.getAllUserBookings(userId));
	}

}
