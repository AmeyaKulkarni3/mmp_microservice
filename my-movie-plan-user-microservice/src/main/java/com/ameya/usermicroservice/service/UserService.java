package com.ameya.usermicroservice.service;

import com.ameya.usermicroservice.dto.UserDto;
import com.ameya.usermicroservice.exception.NoSuchUserException;
import com.ameya.usermicroservice.exception.UserAlreadyExistsException;

public interface UserService{
	
	public UserDto createUser(UserDto user) throws UserAlreadyExistsException;
	public UserDto getUser(String email) throws NoSuchUserException;
	public UserDto getUserById(String userId) throws NoSuchUserException;
	public String login(UserDto dto) throws NoSuchUserException;

}
