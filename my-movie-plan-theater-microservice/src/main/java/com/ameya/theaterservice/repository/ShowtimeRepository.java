package com.ameya.theaterservice.repository;

import java.time.LocalTime;

import com.ameya.theaterservice.entity.Showtime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends CrudRepository<Showtime, Integer> {
	
	Showtime findByTime(LocalTime time);

}
