package com.ameya.theaterservice.repository;

import com.ameya.theaterservice.entity.Theater;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends CrudRepository<Theater, Integer> {
	
	Theater findByName(String name);

}
