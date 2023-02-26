package com.ameya.schedulemicroservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class PartnerDto {
	
	private int id;
	private String name;
	private List<TheaterDto> theaterDtos;

}
