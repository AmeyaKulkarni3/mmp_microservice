package com.ameya.theaterservice.dto;

import lombok.Data;

@Data
public class SeatDto {
	
	private int id;
	private String seatNumber;
	private boolean isBooked;
	private TierDto tier;
	private ScheduleDto schedule;

}
