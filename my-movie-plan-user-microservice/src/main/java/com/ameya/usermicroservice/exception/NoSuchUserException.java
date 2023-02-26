package com.ameya.usermicroservice.exception;

public class NoSuchUserException extends Exception{

	private static final long serialVersionUID = 7073643069172132008L;
	
	public NoSuchUserException() {
		super();
	}
	
	public NoSuchUserException(String errors) {
		super(errors);
	}

}
