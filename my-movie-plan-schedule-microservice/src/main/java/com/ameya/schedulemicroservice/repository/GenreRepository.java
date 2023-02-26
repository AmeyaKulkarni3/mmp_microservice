package com.ameya.schedulemicroservice.repository;

import com.ameya.schedulemicroservice.entity.Genre;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
	
	Genre findByName(String name);

}
