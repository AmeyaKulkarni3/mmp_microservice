package com.ameya.usermicroservice.exception;

public class UserAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -4944015893656596933L;
	
	public UserAlreadyExistsException() {
		super();
	}
	
	public UserAlreadyExistsException(String errors) {
		super(errors);
	}

}
