package com.ameya.usermicroservice.repository;

import com.ameya.usermicroservice.entity.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	public UserEntity findByEmail(String email);
	
	public UserEntity findByUserId(String userId);
}
