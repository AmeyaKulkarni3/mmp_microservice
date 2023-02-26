package com.ameya.theaterservice.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class ShowtimeDto {

	private int id;
	private LocalTime time;
	private List<ScheduleDto> schedules;


}
