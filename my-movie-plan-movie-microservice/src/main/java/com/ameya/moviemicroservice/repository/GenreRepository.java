package com.ameya.moviemicroservice.repository;

import com.ameya.moviemicroservice.entity.Genre;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
	
	Genre findByName(String name);

}
