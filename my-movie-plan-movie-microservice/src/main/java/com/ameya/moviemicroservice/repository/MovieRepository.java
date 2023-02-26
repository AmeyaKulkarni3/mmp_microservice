package com.ameya.moviemicroservice.repository;

import com.ameya.moviemicroservice.entity.Movie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
	
	Movie findByName(String name);

}
