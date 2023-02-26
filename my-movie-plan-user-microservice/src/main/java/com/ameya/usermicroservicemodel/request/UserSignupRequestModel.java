package com.ameya.usermicroservicemodel.request;

import lombok.Data;

@Data
public class UserSignupRequestModel {
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phone;

}
