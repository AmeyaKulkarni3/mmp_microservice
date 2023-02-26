package com.ameya.schedulemicroservice.repository;

import com.ameya.schedulemicroservice.entity.Language;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer> {
	
	Language findByName(String name);

}
