package com.ameya.schedulemicroservice.repository;

import com.ameya.schedulemicroservice.entity.Address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
