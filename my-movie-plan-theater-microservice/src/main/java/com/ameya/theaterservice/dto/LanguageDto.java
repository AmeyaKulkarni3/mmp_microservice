package com.ameya.theaterservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class LanguageDto {
	
	private int id;
	private String name;
	private List<MovieDto> movies;

}
