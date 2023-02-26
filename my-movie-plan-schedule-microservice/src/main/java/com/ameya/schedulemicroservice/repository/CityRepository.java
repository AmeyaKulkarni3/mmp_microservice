package com.ameya.schedulemicroservice.repository;

import com.ameya.schedulemicroservice.entity.City;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
	
	City findByName(String name);

}
