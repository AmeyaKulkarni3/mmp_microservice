package com.ameya.usermicroservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	
	private int id;
	private String firstName;
	private String lastName;
	private String userId;
	private String email;
	private String phone;
	private String password;
	private List<BookingDto> bookings;

}
