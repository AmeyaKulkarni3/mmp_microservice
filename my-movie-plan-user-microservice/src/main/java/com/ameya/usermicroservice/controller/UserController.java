package com.ameya.usermicroservice.controller;

import com.ameya.usermicroservice.dto.UserDto;
import com.ameya.usermicroservice.exception.NoSuchUserException;
import com.ameya.usermicroservice.exception.UserAlreadyExistsException;
import com.ameya.usermicroservice.service.UserService;
import com.ameya.usermicroservicemodel.request.UserLoginRequestModel;
import com.ameya.usermicroservicemodel.request.UserSignupRequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody UserSignupRequestModel user) throws UserAlreadyExistsException {

		UserDto dto = new UserDto();
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setPhone(user.getPhone());
		return ResponseEntity.ok(userService.createUser(dto));
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<String> userLogin(@RequestBody UserLoginRequestModel user) throws NoSuchUserException{
		UserDto dto = new UserDto();
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		return ResponseEntity.ok(userService.login(dto));
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable String userId) throws NoSuchUserException{
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@GetMapping("/users")
	public ResponseEntity<String> getAllUsers(){
		return ResponseEntity.ok("In Get all users");
	}

}
