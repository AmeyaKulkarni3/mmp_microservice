package com.ameya.usermicroservice.dto;

import lombok.Data;

@Data
public class BookingDto implements Comparable<BookingDto> {

	private int id;
	private ScheduleDto schedule;
	private String seats;
	private UserDto user;
	private double totalPrice;
	
	@Override
	public int compareTo(BookingDto o) {
		
		return -getSchedule().getDate().compareTo(o.schedule.getDate());
	}

}
