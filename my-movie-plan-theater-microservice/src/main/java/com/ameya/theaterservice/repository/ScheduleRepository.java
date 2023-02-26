package com.ameya.theaterservice.repository;

import com.ameya.theaterservice.entity.Schedule;

import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
	
}
