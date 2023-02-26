package com.ameya.schedulemicroservice.repository;

import com.ameya.schedulemicroservice.entity.Theater;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends CrudRepository<Theater, Integer> {
	
	Theater findByName(String name);

}
