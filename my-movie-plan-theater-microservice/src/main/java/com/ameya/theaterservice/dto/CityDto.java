package com.ameya.theaterservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class CityDto {
	
	private int id;
	private String name;
	private List<AddressDto> addresses;
	private List<TheaterDto> theatres;

}
