package com.ameya.theaterservice.repository;

import com.ameya.theaterservice.entity.Partner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Integer> {

	Partner findByName(String name);

}
