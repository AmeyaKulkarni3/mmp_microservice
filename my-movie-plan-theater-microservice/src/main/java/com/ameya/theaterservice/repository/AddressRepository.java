package com.ameya.theaterservice.repository;

import com.ameya.theaterservice.entity.Address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
