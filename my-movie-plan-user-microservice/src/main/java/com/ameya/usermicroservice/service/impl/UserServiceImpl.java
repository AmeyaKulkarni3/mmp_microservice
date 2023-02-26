package com.ameya.usermicroservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ameya.usermicroservice.dto.BookingDto;
import com.ameya.usermicroservice.dto.UserDto;
import com.ameya.usermicroservice.entity.Booking;
import com.ameya.usermicroservice.entity.UserEntity;
import com.ameya.usermicroservice.exception.ExceptionConstants;
import com.ameya.usermicroservice.exception.NoSuchUserException;
import com.ameya.usermicroservice.exception.UserAlreadyExistsException;
import com.ameya.usermicroservice.repository.UserRepository;
import com.ameya.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:ValidationMessages.properties")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Environment env;

//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) throws UserAlreadyExistsException {

		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistsException(env.getProperty(ExceptionConstants.USER_ALREADY_EXISTS.toString()));
		}

		user.setUserId(UUID.randomUUID().toString());
		user.setPassword(user.getPassword());
		
		UserEntity entity = new UserEntity();
		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		entity.setPhone(user.getPhone());
		entity.setUserId(user.getUserId());
		entity.setBookings(new ArrayList<>());
		
		UserEntity savedUser = userRepository.save(entity);

		UserDto returnValue = userEntityToDto(savedUser);

		return returnValue;
	}

	@Override
	public UserDto getUser(String email) throws NoSuchUserException {

		UserEntity user = userRepository.findByEmail(email);

		if (user == null) {
			throw new NoSuchUserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.toString()));
		}

		UserDto returnValue = userEntityToDto(user);
		return returnValue;
	}

	@Override
	public UserDto getUserById(String userId) throws NoSuchUserException {
		UserEntity user = userRepository.findByUserId(userId);
		
		if (user == null) {
			throw new NoSuchUserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.toString()));
		}
		
		UserDto returnValue = userEntityToDto(user);
		
		return returnValue;
	}
	
	private UserDto userEntityToDto(UserEntity user) {
		UserDto dto = new UserDto();
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setEmail(user.getEmail());
		dto.setId(user.getId());
		dto.setUserId(user.getUserId());
		dto.setPhone(user.getPhone());
		dto.setPassword(user.getPassword());
		List<Booking> bookings = user.getBookings();
		if(bookings == null) {
			dto.setBookings(new ArrayList<>());
		} else {
			List<BookingDto> bdtos = new ArrayList<>();
			for(Booking b : bookings) {
				BookingDto bdto = new BookingDto();
				bdto.setId(b.getId());
				bdto.setTotalPrice(b.getTotalPrice());
				bdtos.add(bdto);
			}
			dto.setBookings(bdtos);
		}
		return dto;
	}

	@Override
	public String login(UserDto dto) throws NoSuchUserException {
		UserEntity saved = userRepository.findByEmail(dto.getEmail());
		if(saved == null || saved.getPassword().equals(dto.getPassword())) {
			throw new NoSuchUserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.toString()));
		}
		
		return "User Logged in Successfully";
	}

}
