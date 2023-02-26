package com.ameya.schedulemicroservice.repository;

import com.ameya.schedulemicroservice.entity.Movie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
	
	Movie findByName(String name);

}
