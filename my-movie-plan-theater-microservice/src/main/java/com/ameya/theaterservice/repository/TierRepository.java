package com.ameya.theaterservice.repository;

import com.ameya.theaterservice.entity.Tier;

import org.springframework.data.repository.CrudRepository;

public interface TierRepository extends CrudRepository<Tier, Integer>{
	
	Tier findByName(String name);

}
