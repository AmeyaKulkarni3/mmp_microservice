package com.ameya.usermicroservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class TheaterDto {

	private int id;
	private String name;
	private CityDto city;
	private AddressDto address;
	private List<TierDto> tiers;
	private List<ScheduleDto> schedules;


}
