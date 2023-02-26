package com.ameya.schedulemicroservice.repository;

import com.ameya.schedulemicroservice.entity.Tier;

import org.springframework.data.repository.CrudRepository;

public interface TierRepository extends CrudRepository<Tier, Integer>{
	
	Tier findByName(String name);

}
