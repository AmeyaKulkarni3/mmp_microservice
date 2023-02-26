package com.ameya.theaterservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class TierDto implements Comparable<TierDto>{

	private int id;
	private String name;
	private int noOfSeats;
	private double price;
	private int rows;
	private int cols;
	private List<TheaterDto> theaters;
	private int priority;
	private int seatsBooked;
	private List<SeatDto> seats;
	
	@Override
	public int compareTo(TierDto o) {
		if(getPriority() == 0 || o.getPriority() == 0) {
			return 0;
		}
		return getPriority() - o.getPriority();
	}

}
