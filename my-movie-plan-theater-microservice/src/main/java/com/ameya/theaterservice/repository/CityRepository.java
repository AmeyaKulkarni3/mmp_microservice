package com.ameya.theaterservice.repository;

import com.ameya.theaterservice.entity.City;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
	
	City findByName(String name);

}
