package com.ameya.usermicroservice.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ScheduleDto {
	
	private int id;
	private MovieDto movie;
	private TheaterDto theater;
	private ShowtimeDto showtime;
	private List<SeatDto> seats;
	private LocalDate date;

}
