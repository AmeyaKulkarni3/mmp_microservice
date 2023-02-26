package com.ameya.schedulemicroservice.repository;

import com.ameya.schedulemicroservice.entity.Partner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Integer> {

	Partner findByName(String name);

}
