package com.ameya.moviemicroservice.repository;

import com.ameya.moviemicroservice.entity.Language;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer> {
	
	Language findByName(String name);

}
