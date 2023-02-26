package com.ameya.schedulemicroservice.model.request;

import java.util.List;

import lombok.Data;

@Data
public class UpdateSeatStatusModel {
	
	private int scheduleId;
	private List<Integer> seatNumbers;

}
